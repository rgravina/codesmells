class AccountNotOpenException: Error()

class LongMethodAccount {
    private var open = true
    private var balance = 0

    fun balance(): Int {
        if (!open) {
            throw AccountNotOpenException()
        }
        println("customer requested account balance")
        if (balance < 0) {
            // hide this from the customers
            return 0
        }

        // ok, this is the normal case
        return balance
    }
}

class LongMethodAccountRefactored {
    private var open = true
    private var balance = 0

    fun balance(): Int {
        ensureAccountIsOpen()
        logBalanceRequest()
        return customerFacingBalance()
    }

    private fun ensureAccountIsOpen() {
        if (!open) {
            throw AccountNotOpenException()
        }
    }

    private fun logBalanceRequest() {
        println("customer requested account balance")
    }

    private fun customerFacingBalance(): Int {
        if (balance < 0) {
            // hide this from the customers
            return 0
        }
        // ok, this is the normal case
        return balance
    }
}
