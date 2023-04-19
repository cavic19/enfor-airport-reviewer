package org.enfor.models

// TODO: What is the most likely to change? Should it be hardocded?
case class Rating(overallRating: Option[Double], auxiliaryRatings: Map[String, Double])
