package dance.body

object Parts {

  import dance.body._

  private val heads = Set("o", "ő", "ō", "ơ", "ǭ", "δ", "ᐛ", "ᐖ", "ᐕ", "ᐙ", "☠", "☹", "☺", "☻", "♘", "♞", "☭", "⚇", "⚉", "⛑", "⚽", "ツ").map(
    str => BodyPart(North, str, Nil)
  )

  private val torsos = Set("[]", "\\_\\", "/_/", "|_|", "#", "Ô", "Õ", "Ö", "Ŏ", "Ū", "Ǚ", "Ǖ", "Ǿ", "Ȣ", "Ȟ", "Θ", "ᥤ", "ᥪ", "█", "░", "▒", "▓").map(
    str => BodyPart(Central, str, Nil)
  ) +
  BodyPart(Central, "[ƕ", Seq(RightArm)) +
  BodyPart(Central, "[Ƿ", Seq(RightArm)) +
  BodyPart(Central, "Ӌ]", Seq(LeftArm))

  private val leftArms = Set("S", "ſ", "Ɛ", "Ϛ", "Г", "൳", "ᓕ", "ᘛ", "⚞").map(str => BodyPart(West, str, Nil)) ++
  Set("Ɩ", "ƚ", "ᒉ").map(str => BodyPart(Northwest, str, Nil))

  private val rightArms = Set("¬", "Ƨ", "ƪ", "Ƿ", "Ȝ", "ɀ", "Ɂ", "ᄀ", "ᓓ", "ᘚ", "⚟", "フ").map(str => BodyPart(East, str, Nil)) +
  BodyPart(Northeast, "ᒋ", Nil)

  private val leftLegs = Set("/", "!", "ᨆ", "C", "ſ", "ረ", "Ꮁ", "ᒍ", "ᓯ", "∠").map(str => BodyPart(Southwest, str, Nil)) ++
  Set("__", "\\").map(str => BodyPart(West, str, Nil))

  private val rightLegs = Set("\\", "!", "ᨆ", "L", "Ɩ", "ᒐ", "ᒪ", "ᓭ", "ᖚ").map(str => BodyPart(Southeast, str, Nil)) ++
  Set("__", "/").map(str => BodyPart(East, str, Nil))

  private val combinedLegs = Set("Ω", "X").map(str => BodyPart(South, str, Nil))

  val parts = Map[BodyPartSlot, Set[BodyPart]](
    Head -> heads,
    Torso -> torsos,
    LeftArm -> leftArms,
    RightArm -> rightArms,
    LeftLeg -> leftLegs,
    RightLeg -> rightLegs,
    Legs -> combinedLegs
  )
}
