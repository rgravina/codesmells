import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MysteriousNameTests {
    @Test
    internal fun mysteriousName() {
        val mysteriousName = MysteriousName()
        assertEquals(7.40, mysteriousName.exchange(1000), 0.01)
    }

    @Test
    internal fun mysteriousNameRefactored() {
        val mysteriousName = MysteriousNameRefactored()
        assertEquals(7.40, mysteriousName.exchangeToUSD(1000), 0.01)
    }
}