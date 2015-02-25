package object phenotype {

  import body._
  import body.Parts.parts
  import scala.collection.immutable.HashMap


  def typical(): Body = {
    addBodyParts(
      new HashMap[BodyPartSlot, BodyPart],
      List(
        BodyAddition(Head, parts.get(Head)
          .flatMap(head => head.headOption)
          .getOrElse(BodyPart(North, "o", Nil))),
        BodyAddition(Torso, BodyPart(Central, "U", Nil))
      )
    )
  }
}
