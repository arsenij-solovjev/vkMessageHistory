package jinesra.vkMessageHistory

import net.liftweb.json._
object Analyzer {
  
  def getFromIds(path: String): List[String] = {
    val contents = IO.readFromFile(path)
    val json = JsonParser.parse(contents)
    val fromIds = (json \\ "from_id").children.
      map(child => child.extract[String])
    fromIds
  }

  def getBodies(path: String): String = {
    val contents = IO.readFromFile(path)
    val json = JsonParser.parse(contents)
    val bodies = (json \\ "body").children.
      map(child => child.extract[String]).
      fold("")((wholeString, newLine) => wholeString ++ newLine)
    bodies
  }

  def wordHisto(path: String) = {
    var m: Map[String, Int] = Map()
    val fromIds = getBodies(path).split(Array(' ', '?', '.', '!'))
    fromIds.foreach {
      id =>
        if (m.contains(id))
          m += (id -> (m(id) + 1))
        else
          m += (id -> 1)
    }
    m
  }

  def fromIdHisto(path: String) = {
    var m: Map[String, Int] = Map()
    val fromIds = getFromIds(path)
    fromIds.foreach {
      id =>
        if (m.contains(id))
          m += (id -> (m(id) + 1))
        else
          m += (id -> 1)
    }
    m
  }

  case class FromId(from_id: String)
} 