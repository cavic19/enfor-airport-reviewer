package org.enfor.repository

import org.enfor.models.{Airport, Review}
import scala.concurrent.Future

trait ReviewRepository {
  def listAll: Future[List[Review]]
  def listAll(airport: Airport): Future[List[Review]]
}
