import org.junit.jupiter.api.Test

class DivergentChangeTests {
    @Test
    internal fun orderPizza() {
        val order = DivergentChangePizzaOrder()
        order.add("super special")
        order.add("diablo")
        val card = SpyCard()
        order.checkout(card)
        assert(order.paid)
        assert(card.charged)
    }
}
