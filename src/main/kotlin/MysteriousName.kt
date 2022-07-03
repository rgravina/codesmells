/*
  Naming is important! Look at the improvement in readability gained just by renaming a few things.
  It only took a very short time to do, and the IDE did the manual work of ensuring correctness.
  We also have unit tests to verify the behaviour we do care about didn't change.
  While renaming, it became clearer that the exchange rate could be moved to a field and that the conversion could be inlined.
  Simple refactorings often reveal more things you can do.
  Don't underestimate the value of a good name.
 */
class MysteriousName {
    fun exchange(value: Int): Double {                      // What is being exchanged from and to, and what is value?
        val newValue = value / 135.0                        // What does new value mean? What is this constant?
        return newValue                                     // Do we need this temporary variable?
    }
}

class MysteriousNameRefactored {
    private val jpyToUsdRate = 135.0                            // The constant is now clearly the JPY to USD rate

    fun exchangeToUSD(amountToConvertInJpy: Int): Double {      // This function exchanges JPY to USD and it is clear from the name what it does.
        return amountToConvertInJpy / jpyToUsdRate              // The conversion logic is clearer now
    }

    // Note: There is no need to support converting from any currency to USD in our app, so don't over-engineer at this point.
    // That is a code smell too, called Speculative Generality. XP warns against this practice with the term YAGNI (You Ain't Going to Need It)
}
