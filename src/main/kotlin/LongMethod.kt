/*
  There are a few steps in this method, but it's not clear where one starts and ends.
  In practice, you may see a lot of code like this. To the person who wrote it at the time, it might look OK.
  But to someone looking at it for the first time, you have to read the whole function to understand what it does.
*/
class AccountNotOpenException : Error()
class LongMethodAccount {
    private var open = true
    private var balance = 0

    fun balance(): Int {
        if (!open) {                                                // Seems like some precondition checking.
            throw AccountNotOpenException()
        }
        println("acc: customer requested account balance")          // Why is this println here? Was it for debugging?
        if (balance < 0) {                                          // There is some interesting logic here
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
        ensureAccountIsOpen()                           // Now it's clear that there is application logic here
        logBalanceRequest()                             // The println was really an important logging step we need
        return customerFacingBalance()                  // The logic was extracted to a function with a good name
    }

    private fun ensureAccountIsOpen() {
        if (!open) {
            throw AccountNotOpenException()
        }
    }

    private fun logBalanceRequest() {                   // The "acc" prefix seems important for logging, so it could also be extracted to a logging class.
        println("acc: customer requested account balance")
    }

    private fun customerFacingBalance(): Int {          // So now we can see that the logic is to get the customer facing balance
        if (balance < 0) {                              // The comments are still required to understand it, so it's a sign more refactoring is needed
            // hide this from the customers             // Comments in the code are also another code smell. It's better to use descriptive names if possible.
            return 0
        }
        // ok, this is the normal case
        return balance
    }
}
/*
  There are many other ways a long method can be refactored. It depends on the reason why the method is long.
  This example fixes a long method that contains several steps by making the steps clearer using private methods.
  Some other reason methods might be long include that method does not have a single responsibility, the parameter list
  might be too long, it contains a long or complex if statement, and many more.
 */
