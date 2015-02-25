package body

package object phenotype {

  import body._
  import body.Parts.parts
  import scala.collection.immutable.HashMap
  import scala.util.Random


  def typical(): Body = {
    addBodyParts(
      new HashMap[BodyPartSlot, BodyPart],
      List(
        randomPart(Head, BodyPart(North, "o", Nil)),
        randomPart(LeftLeg, BodyPart(Southwest, "/", Nil)),
        randomPart(RightLeg, BodyPart(Southeast, "\\", Nil)),
        randomPart(LeftArm, BodyPart(West, "/", Nil)),
        randomPart(RightArm, BodyPart(East, "\\", Nil)),
        randomPart(Torso, BodyPart(Central, "U", Nil))
      )
    )
  }

  private def randomPart(slot: BodyPartSlot, default: BodyPart): BodyAddition =
    BodyAddition(slot, parts.get(slot)
      .flatMap(slotOptions => {
        Random.shuffle(slotOptions.toList).headOption
      })
      .getOrElse(default))
}
