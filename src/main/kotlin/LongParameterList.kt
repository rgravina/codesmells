import java.time.LocalDate

/*
  When a method has many parameters it often hides how they are related.
  You might also find the same groups of parameters used in several places.
 */
class LongParameterList {
    fun register(firstName: String, lastName: String, email: String, joinMailingList: Boolean) {
    }

    fun getRecentRegistrations(from: LocalDate, to: LocalDate) {
    }
}

/*
  Rather than pass all the pieces of data that make up a new user registration, grouping them makes it clear what is required.
  A class also provides a place to add other functionality that might be related to a new user registration, such as validation.
 */
data class UserRegistration(
    val firstName: String,
    val lastName: String,
    val email: String,
    val joinMailingList: Boolean
)

/*
  Even though the parameter list was short, grouping from and to into a DateRange class clearly shows what the parameters
  mean, and allows DateRange to be used in other places in the code. A DateRange always has a from and to date, and this
  puts the rule in code. If things change, and one of the dates become optional, that can be defined in one place.
 */
data class DateRange(val from: LocalDate, val to: LocalDate)

class LongParameterListRefactored {
    fun register(user: UserRegistration) {
    }

    fun getRecentRegistrations(range: DateRange) {
    }
}
