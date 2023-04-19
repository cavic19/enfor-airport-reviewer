package org.enfor.controllers

import org.enfor.models.{Airport, ReviewStatistic, ReviewStatisticsSummary}
import org.enfor.services.ReviewStatisticService

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}
import play.api.libs.json._
import play.api.mvc.Result
import play.api.mvc.Results.Ok
@Singleton
class ReviewStatisticController @Inject()(val reviewStatisticService: ReviewStatisticService){
  
  def getStatisticsSummary(implicit ec: ExecutionContext): Future[Result] = {
    reviewStatisticService.getStatisticsSummary.map(summaries =>
      Ok(Json.toJson(summaries.toList))
    )
  }

  def getStatistic(airportKey: String)(implicit ec: ExecutionContext): Future[Result] = {
    reviewStatisticService.getStatistic(Airport(airportKey)).map(summary =>
      Ok(Json.toJson(summary))
    )
  }
}
