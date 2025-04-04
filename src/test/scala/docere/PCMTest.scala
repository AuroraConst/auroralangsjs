package docere
import org.scalatest._
import wordspec._
import matchers._
import testingutils.*
import SjsAst.*
import catsgivens.{given}

import cats.syntax.semigroup._ // for |+|

class PCMTest extends wordspec.AsyncWordSpec with should.Matchers :
  override implicit def executionContext = global

  
  val issues1a = testIssuesAurora(0)
  val issues1b = testIssuesAurora(1)

  "test files issues1a.aurora and issues1b.aurora" should {
    "exist" in {
      Future{
        checkFileAccess(issues1a) should be(true)
        checkFileAccess(issues1b) should be(true)
      }
    }
  }

  "PCM" should { "work" in {
    parse(issues1a).toFuture
    .map{ pcm =>
      pcm.elements.foreach{ node => 
      info(f"Asttype: ${node.$type}%16s ") }

      true should be(true)
    }
  }


  "PCM1" should {"work" in {
    parse(issues1a).toFuture
    .map{ pcm =>
      val result = SjsAst.PCM(pcm)
      println(s"$result")
      println(s"${result.text}")
      true should be(true)
    }

  }}

  "NGO+NGO" should {"work" in {

    val coordMapX = List(RefCoordinate("x"), RefCoordinate("y")).map{r => r.id -> r}.toMap
    val coordMapY = List(RefCoordinate("z"), RefCoordinate("y")).map{r => r.id -> r}.toMap
    val ocoordX = Set(OrderCoordinate("a", coordMapX))
    val ocoordY = Set(OrderCoordinate("a", coordMapX))

    // lift NGO into a MAP
    val ngoX = List(NGO("ngo1", ocoordX)).map {n => n.id -> n}.toMap
    val ngoY = List(NGO("ngo1", ocoordY)).map {n => n.id -> n}.toMap

    val ordersX = Orders(ngoX)
    val ordersY = Orders(ngoY)
    
    Future{

      

      val result = ordersX |+| ordersY
      val s = pprint.apply(result)
      info(s"$s")
      

      // val pretty = ngoPrettifier(result)
      // info(s"***${pretty}")
      // info(s"$result")
      pending
    }
  }}

  "PCM+PCM" should { "work" in {
      import cats.syntax.semigroup._ // for |+|
      for{
        pcm1 <- parse(issues1a).toFuture
        pcm2 <- parse(issues1b).toFuture
      }
      yield {
        // val result = SjsAst.PCM(pcm1) |+| SjsAst.PCM(pcm2)


        // println(result.cio("Orders").asInstanceOf[SjsAst.Orders].ngo)
        // val x = SjsAst.PCM(pcm1).cio("Orders").asInstanceOf[SjsAst.Orders].ngo.map{(k,v) => k -> v.set}
        // println(result.text)
        pending
      }
    }}}