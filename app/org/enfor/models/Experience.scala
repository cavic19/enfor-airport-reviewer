package org.enfor.models

abstract class Experience(val displayName: String)

object Experience {
  case object ArrivalOnly extends Experience("Arrival Only")

  case object ArrivalAndDeparture extends Experience("Arrival and Departure")

  case object DepartureOnly extends Experience("Departure Only")

  case object Transit extends Experience("Transit")

  case object Unknown extends Experience("")
  def fromString(string: String): Experience = string match {
    case ArrivalOnly.displayName => ArrivalOnly
    case ArrivalAndDeparture.displayName => ArrivalAndDeparture
    case DepartureOnly.displayName => DepartureOnly
    case Transit.displayName => Transit
    case _ => Unknown
  }
}
