package dance.body

package object phenotype {

  import dance.body._
  import dance.body.Parts.parts
  import scala.collection.immutable.HashMap
  import scala.util.Random


  def typical(): Body = {
    List(
      () => randomPart(Head, BodyPart(North, "o", Nil)),
      () => randomPart(LeftLeg, BodyPart(Southwest, "/", Nil)),
      () => randomPart(RightLeg, BodyPart(Southeast, "\\", Nil)),
      () => randomPart(LeftArm, BodyPart(West, "/", Nil)),
      () => randomPart(RightArm, BodyPart(East, "\\", Nil)),
      () => randomPart(Torso, BodyPart(Central, "U", Nil))
    ).foldLeft[Body](new HashMap[BodyPartSlot, BodyPart]){ (body, partGetter) => retryAddPart(partGetter, body) }
  }

  def combinedLegs(): Body = {
    List(
      () => randomPart(Head, BodyPart(North, "o", Nil)),
      () => randomPart(Legs, BodyPart(South, "X", Nil)),
      () => randomPart(LeftArm, BodyPart(West, "/", Nil)),
      () => randomPart(RightArm, BodyPart(East, "\\", Nil)),
      () => randomPart(Torso, BodyPart(Central, "U", Nil))
    ).foldLeft[Body](new HashMap[BodyPartSlot, BodyPart]){ (body, partGetter) => retryAddPart(partGetter, body) }
  }

  private def retryAddPart(partGetter: () => BodyAddition, body: Body): Body = {
    val addition = partGetter()
    if (body.filter({ case (slot, part) =>
      part.area == addition.part.area
    }).isEmpty)
      addBodyPart(body, addition)
    else
      retryAddPart(partGetter, body)
  }

  private def randomPart(slot: BodyPartSlot, default: BodyPart): BodyAddition =
    BodyAddition(slot, parts.get(slot)
      .flatMap(slotOptions => {
        Random.shuffle(slotOptions.toList).headOption
      })
      .getOrElse(default))
}
