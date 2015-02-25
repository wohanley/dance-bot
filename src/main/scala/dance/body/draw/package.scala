package dance.body

package object draw {

  import dance.body._


  case class DrawableBodyPart(draw: String, offset: Int)

  type DrawableBody = List[List[DrawableBodyPart]]


  def draw(body: Body): String = draw(drawablify(body))

  private def draw(body: DrawableBody): String = {
    body.map(row =>
      row.foldLeft("") { (str, part) =>
        str.padTo(part.offset, " ").mkString + part.draw
      }
    ).mkString("\n")
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
    val legPositions = legOffsets.getOrElse(centerLength, LegOffset(0, 0, 0))

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
        getDrawableForArea(body, Southwest, centerOffset + legPositions.left),
        getDrawableForArea(body, South, centerOffset + legPositions.combined),
        getDrawableForArea(body, Southeast, centerOffset + legPositions.right)
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

  case class LegOffset(left: Int, combined: Int, right: Int)

  /** Leg offsets vary depending on the width of the body. */
  private val legOffsets = Map[Int, LegOffset](
    0 -> LegOffset(0, 0, 0),
    1 -> LegOffset(-1, 0, 1),
    2 -> LegOffset(0, 0, 1),
    3 -> LegOffset(0, 1, 2)
  )
}
