package docere.sjsast

import scala.scalajs.js

case class ClinicalCoordinateValue(
  name: String,
  narrative: Set[Narrative] = Set.empty,
  refs: Set[RefCoordinate] = Set.empty
) extends SjsNode:

  def merge(cc: ClinicalCoordinateValue): ClinicalCoordinateValue =
    val narratives = narrative |+| cc.narrative
    val result = refs |+| cc.refs
    ClinicalCoordinateValue(name, narratives, result)

  override def merge(p: SjsNode): SjsNode =
    merge(p.asInstanceOf[ClinicalCoordinateValue])

object ClinicalCoordinateValue:
  def apply(c: GenAst.ClinicalCoordinateValue): ClinicalCoordinateValue =
    val dyn = c.asInstanceOf[js.Dynamic]

    val name = dyn.selectDynamic("name").asInstanceOf[String]

    val narratives = dyn.selectDynamic("narrative")
      .asInstanceOf[js.Array[js.Dynamic]]
      .map(n => Narrative(n.selectDynamic("name").asInstanceOf[String]))
      .toSet

    val refs = dyn.selectDynamic("refs")
      .asInstanceOf[js.Array[js.Dynamic]]
      .map(r => RefCoordinate(r.selectDynamic("$refText").asInstanceOf[String]))
      .toSet

    ClinicalCoordinateValue(name, narratives, refs)
