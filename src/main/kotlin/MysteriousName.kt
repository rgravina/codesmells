/*
  Naming is important! Look at the improvement in readability gained just by renaming a few things.
  It only took a very short time to do, and the IDE did the manual work of ensuring correctness.
  We also have unit tests to verify the behaviour we do care about didn't change.
  While renaming, it became clearer that the exchange rate could be moved to a field and that the conversion could be inlined.
  Simple refactorings often reveal more things you can do.
  Don't underestimate the value of a good name.
 */
class MysteriousName {
    // What is being exchanged from and to?
    // What is value here?
    fun exchange(value: Int): Double {
        // What does new value mean?
        // What is this constant?
        val newValue = value / 135.0
        // Do we need this temporary variable?
        return newValue
    }
}

class MysteriousNameRefactored {
    // The constant is now clearly the JPY to USD rate
    private val jpyToUsdRate = 135.0

    // This function exchanges JPY to USD.
    // This is a refactor. It made the purpose of the function clearer.
    // There is no need to support converting from any currency to USD in our app, so don't over-engineer at this point.
    // That is a code smell too, called Speculative Generality. XP warns against this practice with the term YAGNI (You Ain't Going to Need It)
    fun exchangeToUSD(amountToConvertInJpy: Int): Double {
        // The conversion logic is clearer now
        return amountToConvertInJpy / jpyToUsdRate
    }
}
