package org.enfor.services

import org.enfor.models.Airport
import org.enfor.repository.ReviewRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class ReviewService @Inject()(val reviewRepository: ReviewRepository){
  def getReviewsChronologically(airport: Airport)(implicit ec: ExecutionContext) =
    reviewRepository.listAll.map(reviews => reviews.sortBy(_.date.getMillis))
}
