package body

object Parts {

  import body._

  private val heads = Set("o", "ő", "ō", "ơ", "ǭ", "ͼ", "ͽ", "δ", "ᄆ", "ᄋ", "ᐛ", "ᐖ", "ᐕ", "ᐙ", "ᨔ", "※", "⁕", "⊕", "⊖", "⊗", "⊘", "⊙", "⊚", "⊛", "⊜", "⊝", "⊞", "⊟", "⊠", "⊡", "☠", "☹", "☺", "☻", "♘", "♞", "☭", "⚇", "⚉", "⛑", "⚽", "ツ").map(
    str => BodyPart(North, str, Nil)
  )

  private val torsos = Set("[]", "\\_\\", "/_/", "|_|", "#", "Ô", "Õ", "Ö", "Ŏ", "Ū", "Ǚ", "Ǖ", "Ǿ", "Ȣ", "Ȟ", "Θ", "ᥤ", "ᥪ", "█", "░", "▒", "▓").map(
    str => BodyPart(Central, str, Nil)
  )

  val parts = Map[BodyPartSlot, Set[BodyPart]](
    Head -> heads,
    Torso -> torsos
  )
}
