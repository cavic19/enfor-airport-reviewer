package org.enfor.controllers

import org.enfor.models.{Airport, Review}
import org.enfor.services.ReviewService
import play.api.libs.json._
import play.api.mvc.Result
import play.api.mvc.Results.Ok

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ReviewController @Inject()(val reviewService: ReviewService){
  def getAirportReview(airportKey: String)(implicit ec: ExecutionContext): Future[Result] =
    reviewService.getReviewsChronologically(Airport(airportKey)).map(reviews =>
      Ok(Json.toJson(reviews))
    )

}
