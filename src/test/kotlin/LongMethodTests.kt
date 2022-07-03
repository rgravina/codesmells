import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LongMethodTests {
    @Test
    fun returnsBalance() {
        val account = LongMethodAccount()
        assertEquals(0, account.balance())
    }

    @Test
    fun returnsBalanceAfterRefactoring() {
        val account = LongMethodAccountRefactored()
        assertEquals(0, account.balance())
    }
}
