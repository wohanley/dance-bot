package object body {

  /** A body has a certain set of slots to be occupied by body parts. Note that
    * a single body doesn't need all these slots (and in fact, can't have them
    * all and still make sense). */
  sealed trait BodyPartSlot
  case object Head extends BodyPartSlot
  case object Torso extends BodyPartSlot
  case object LeftArm extends BodyPartSlot
  case object RightArm extends BodyPartSlot
  case object Legs extends BodyPartSlot // both legs can be a single character
  case object LeftLeg extends BodyPartSlot
  case object RightLeg extends BodyPartSlot

  /** A body part is displayed in a certain place. */
  sealed trait BodyPartDisplayArea
  case object Central extends BodyPartDisplayArea
  case object North extends BodyPartDisplayArea
  case object Northeast extends BodyPartDisplayArea
  case object East extends BodyPartDisplayArea
  case object Southeast extends BodyPartDisplayArea
  case object South extends BodyPartDisplayArea
  case object Southwest extends BodyPartDisplayArea
  case object West extends BodyPartDisplayArea
  case object Northwest extends BodyPartDisplayArea


  case class BodyPartDisplay(area: BodyPartDisplayArea, string: String)

  /** @param exclude specifies some body part slots that this part can't coexist    * with */
  case class BodyPart(
    area: BodyPartDisplayArea,
    display: String,
    exclude: Traversable[BodyPartSlot]
  )

  type Body = Map[BodyPartSlot, BodyPart]

  case class BodyAddition(slot: BodyPartSlot, part: BodyPart)

  /** This method might possibly return a map that's been changed from param
    * parts in more ways than by just adding a kv-pair, depending on the
    * contents of the body part rule. */
  def addBodyPart(body: Body, add: BodyAddition) = {
    // we need to remove all the slots that this rule needs excluded, then add
    // the part it needs added
    (body -- add.part.exclude) + (add.slot -> add.part)
  }

  /** Add the parts listed in partsToAdd to the map parts. The order of
    * partsToAdd is important! A body part can be re-added after it's been
    * excluded. */
  def addBodyParts(body: Body, partsToAdd: List[BodyAddition]): Body =
    partsToAdd match {
      case Nil => body
      case partToAdd :: restPartsToAdd =>
        addBodyParts(addBodyPart(body, partToAdd), restPartsToAdd)
    }
}
