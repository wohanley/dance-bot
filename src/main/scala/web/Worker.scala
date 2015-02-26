package web

import dance.body._
import scala.util.Failure
import scala.util.Success
import scala.util.Try


object Worker {

  def main(args: Array[String]) {
    tweetRegularly()
  }

  private def tweetRegularly() = {
    while (true) {
      web.tweet("‌" + danceMove.getOrElse("*falls down*"))
      Thread.sleep(60 * 1000)
    }
  }

  private def danceMove(): Option[String] = {
    Try(draw.draw(phenotype.typical())) match {
      case Success(drawn) => Some(drawn)
      case Failure(_) => None
    }
  }
}
