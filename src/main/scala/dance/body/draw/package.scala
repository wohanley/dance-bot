package body

package object draw {

  import body._


  case class DrawableBodyPart(draw: String, offset: Int)

  type DrawableBody = List[List[DrawableBodyPart]]


  def draw(body: DrawableBody): String = {
    body.map(row => row.foldLeft("") { (str, part) =>
      str.padTo(part.offset, " ") + part.draw
    }).mkString("\n")
  }

  private def drawablify(body: Body): DrawableBody = {

    val centerLength = getDisplayForArea(body, Central).length

    val centerOffset = body.filter(kv => kv match {
      case (_, part) => List(Northwest, West, Southwest).contains(part.area)
    }).map(kv => kv match {
      case (_, part) => part.display.length
    }).max

    val leftOfCenter = centerOffset + (if (centerLength % 2 == 0) 0 else -1)
    val rightOfCenter = leftOfCenter + centerLength + (if (centerLength % 2 == 0) -1 else 0)

    List(
      List(
        getDrawableForArea(body, Northwest, leftOfCenter - getDisplayForArea(body, Northwest).length),
        getDrawableForArea(body, North, centerOffset),
        getDrawableForArea(body, Northeast, rightOfCenter)
      ),
      List(
        getDrawableForArea(body, West, leftOfCenter - getDisplayForArea(body, West).length),
        getDrawableForArea(body, Central, centerOffset),
        getDrawableForArea(body, East, rightOfCenter)
      ),
      List(
        getDrawableForArea(body, Southwest, leftOfCenter - getDisplayForArea(body, Southwest).length),
        getDrawableForArea(body, South, centerOffset),
        getDrawableForArea(body, Southeast, rightOfCenter)
      )
    )
  }

  private def getDisplayForArea(body: Body,
    area: BodyPartDisplayArea): String = {
    body.find(_._2.area == area).map(_._2.display).getOrElse("")
  }

  private def getDrawableForArea(body: Body, area: BodyPartDisplayArea,
    offset: Int): DrawableBodyPart =
    DrawableBodyPart(getDisplayForArea(body, area), offset)
}
