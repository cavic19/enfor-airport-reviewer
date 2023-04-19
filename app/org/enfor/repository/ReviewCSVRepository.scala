package org.enfor.repository

import com.github.tototoshi.csv._
import org.enfor.models.{Airport, Experience, Rating, Review, User}
import org.joda.time.DateTime

import scala.concurrent.{ExecutionContext, Future}
import java.io.File
import javax.inject.Singleton

@Singleton
class ReviewCSVRepository extends ReviewRepository {
  private val CVSFileLocation = sys.env.getOrElse("CSV_PATH", throw new NoSuchElementException("Env. variable CSV_PATH hasn't been set"))


  // Not well testable, if there was more time I could implement own CSV parser, using shapeless, and type induction, or just review better libraries, that accepts strings, or list of lines instead of file.
  override def listAll(implicit ec: ExecutionContext): Future[List[Review]] = {
    val reviews =  CSVReader.open(new File(CVSFileLocation)).all().map {
      case Seq(
      airport_name,
      link,
      title,
      author,
      author_country,
      date,
      content,
      experience_airport,
      date_visit,
      type_traveller,
      overall_rating,
      queuing_rating,
      terminal_cleanliness_rating,
      terminal_seating_rating,
      terminal_signs_rating,
      food_beverages_rating,
      airport_shopping_rating,
      wifi_connectivity_rating,
      airport_staff_rating,
      recommended
      ) => Review(
        title = title,
        content = content,
        author = User(author, author_country),
        airport = Airport(airport_name),
        date = DateTime.parse(date),
        experience = Experience.fromString(experience_airport),
        recommends = recommended match {
          case "0" => Some(false)
          case "1" => Some(true)
          case _ => None
        },
        rating = Rating(
          overallRating = stringToOption(overall_rating),
          auxiliaryRatings = Map(
            "queuing_rating" -> queuing_rating,
            "terminal_cleanliness_rating" -> terminal_cleanliness_rating,
            "terminal_seating_rating" -> terminal_seating_rating,
            "terminal_signs_rating" -> terminal_signs_rating,
            "food_beverages_rating" -> food_beverages_rating,
            "airport_shopping_rating" -> airport_shopping_rating,
            "wifi_connectivity_rating" -> wifi_connectivity_rating,
            "airport_staff_rating" -> airport_staff_rating,
          ).collect { case (key, rating) if rating != "" => key -> rating.toDouble }
        ),
      )
    }
    Future.successful(reviews)
  }

  override def listAll(airport: Airport)(implicit ec: ExecutionContext): Future[List[Review]] = listAll.filter

  private def stringToOption(string: String): Option[String] = Option(string).filter(_.nonEmpty)
}



