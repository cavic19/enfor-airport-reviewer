package org.enfor.models

import play.api.libs.json.{Json, OFormat}

case class ReviewStatistic(airport: Airport, reviewCount: Int, averageOverallRating: Double, recommendations: Int)

object ReviewStatistic {
  implicit val statisticFormatter: OFormat[ReviewStatistic] = Json.format[ReviewStatistic]
}