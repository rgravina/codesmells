class DivergentChangePizzaOrder {
    var paid = false
    var pizzas = arrayListOf<String>()

    fun add(pizza: String) {
        pizzas.add(findProduct(pizza))
    }

    private fun findProduct(pizza: String): String {
        println("checking the pantry")
        return pizza
    }

    private fun cookProduct(pizza: String) {
        println("put $pizza in the oven")
    }

    fun checkout(card: Card) {
        for (pizza in pizzas) {
            cookProduct(pizza)
        }
        card.charged = true
        paid = true
    }
}


class DivergentChangePizzaAndBeerOrder {
    var paid: Boolean = false
    var products = arrayListOf<String>()

    fun add(product: String) {
        if (product.contains("beer")) {
            products.add(findBeer(product))
        } else {
            products.add(findPizza(product))
        }
    }

    private fun findPizza(pizza: String): String {
        println("checking the pantry")
        return pizza
    }

    private fun findBeer(beer: String): String {
        println("checking the fridge")
        return beer
    }

    private fun cookPizza(pizza: String) {
        println("put $pizza in the oven")
    }

    private fun serveBeer(beer: String) {
        println("pour $beer carefully into a glass")
    }

    fun checkout(card: Card) {
        for (product in products) {
            if (product.contains("beer")) {
                serveBeer(product)
            } else {
                cookPizza(product)
            }
        }

        // pizzas online ordering ok, beers must be paid in cash in person
        if (products.first { it.contains("beer") }.isNotEmpty()) {
            println("pay in cash later")
            card.charged = false
            paid = false
        } else {
            card.charged = true
            paid = true
        }
    }
}

interface Card {
    var charged: Boolean
}

interface Product {
    fun find(): Product
    fun prepare()
    fun cashOnly(): Boolean
}

class Pizza(private val name: String): Product {
    override fun find(): Product {
        println("checking the pantry")
        return this
    }

    override fun prepare() {
        println("put $name in the oven")
    }

    override fun cashOnly(): Boolean {
        return false
    }
}

class Beer(private val name: String): Product {
    override fun find(): Product {
        println("checking the fridge")
        return this
    }

    override fun prepare() {
        println("pour $name carefully into a glass")
    }

    override fun cashOnly(): Boolean {
        return true
    }
}

class DivergentChangePizzaAndBeerOrderRefactored {
    var paid: Boolean = false
    var products = arrayListOf<Product>()

    fun add(product: Product) {
        products.add(product.find())
    }

    fun checkout(card: Card) {
        for (product in products) {
            product.prepare()
        }

        if (products.firstOrNull() { it.cashOnly() } !== null ) {
            println("pay in cash later")
            card.charged = false
            paid = false
        } else {
            card.charged = true
            paid = true
        }
    }
}
