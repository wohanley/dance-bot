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


  /** When we add a part to the body, we might also need to do something more.
    *
    * @param exclude specifies some body part slots that this part can't coexist
    * with */
  case class BodyPartRule(display: String, exclude: Traversable[BodyPartSlot])


  /** This method might possibly return a map that's been changed from param
    * parts in more ways than by just adding a kv-pair, depending on the
    * contents of the body part rule. */
  def addBodyPart(parts: Map[BodyPartSlot, String], add: BodyPartRule) = {
    parts
  }

  def addBodyParts(parts: Map[BodyPartSlot, String],
    partsToAdd: List[BodyPartRule]): Map[BodyPartSlot, String] =
    partsToAdd match {
      case Nil => parts
      case partToAdd :: restPartsToAdd =>
        addBodyParts(addBodyPart(parts, partToAdd), restPartsToAdd)
    }
}
