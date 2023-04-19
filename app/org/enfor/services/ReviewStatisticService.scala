package org.enfor.services

import org.enfor.models.{Airport, ReviewStatistic, ReviewStatisticsSummary}
import org.enfor.repository.ReviewRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class ReviewStatisticService @Inject()(val reviewRepository: ReviewRepository) {
  def getStatistics(implicit ec: ExecutionContext) = for {
    review <- reviewRepository.listAll
  } yield review.groupBy(_.airport).map {
    case (airport, reviews) =>
      val submittedOverallReviews = reviews.flatMap(_.rating.overallRating)
      val submittedRecommendations = reviews.flatMap(_.recommends).count(b => b)
      ReviewStatistic(airport, reviews.size, submittedOverallReviews.sum / submittedOverallReviews.size, submittedRecommendations)
  }


  def getStatistic(airport: Airport)(implicit ec: ExecutionContext) =
    getStatistics.map(statistics => statistics.filter(_.airport == airport).head)


  def getStatisticsSummary(implicit ec: ExecutionContext) = getStatistics.map(reviews =>
    reviews.map {
      case ReviewStatistic(Airport(airportName), reviewCount, _, _) => ReviewStatisticsSummary(airportName, reviewCount)
    }
  )
}

