import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LongMethodTests {
    @Test
    fun returnsBalance() {
        val account = LongMethodAccount()
        assertEquals(account.balance(), 0)
    }

    @Test
    fun returnsBalanceAfterRefactoring() {
        val account = LongMethodAccountRefactored()
        assertEquals(account.balance(), 0)
    }
}
