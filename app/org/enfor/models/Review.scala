package org.enfor.models

import org.joda.time.DateTime

case class Review(title: String,
                  content: String,
                  author: User,
                  airport: Airport,
                  rating: Rating,
                  date: DateTime,
                  recommends: Boolean,
                  experience: Experience
                 )



