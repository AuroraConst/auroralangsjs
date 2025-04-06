package docere

import SjsAst.*
object contructors :
  val issueCoordsX = List("a","b")
  val issueCoordsY = List("b","c")

  val ref1X = List("a")
  val ref2X = List("a","b")

  val ref1Y = List("b")
  val ref2Y = List("b","c")

  val ngoX = List("NGOX1","NGOX2")
  val ngoY = List("NGOX1","NGOY2")


  def refs(names:Set[String]) = names.toSet.map{RefCoordinate(_)}
  def ocoords(names:Set[String],refs:Set[RefCoordinate] ) = names.toSet.map{x => OrderCoordinate(x, refs)}
  