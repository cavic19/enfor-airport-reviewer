package org.enfor.models

import play.api.libs.json.{Json, OFormat}

case class ReviewStatisticsSummary(airportName: String, reviewCount: Int)
object ReviewStatisticsSummary {
  implicit val summaryFormatter: OFormat[List[ReviewStatisticsSummary]] = Json.format[List[ReviewStatisticsSummary]]
}