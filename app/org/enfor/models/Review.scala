package org.enfor.models

import org.joda.time.DateTime
import play.api.libs.json.{Json, OFormat}

case class Review(title: String,
                  content: String,
                  author: User,
                  airport: Airport,
                  rating: Rating,
                  date: DateTime,
                  recommends: Option[Boolean],
                  experience: Experience
                 )

object Review {
  implicit val reviewsFormatter: OFormat[List[Review]] = Json.format[List[Review]]
}


