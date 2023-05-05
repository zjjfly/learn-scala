package com.github.zjjfly.ls.chapt6

import scala.io.Source
import scala.util.matching.Regex
import scala.language.postfixOps

/**
  * Created by zjjfly on 16/3/29.
  */
object BigExercise {
  def main(args: Array[String]) {
    val url =
      "http://api.openweathermap.org/data/2.5/forecast?mode=xml&lat=31.3&lon=120.6&appid=e4f62ff92c4b71d4919db54a210bb320"
    val list: List[String] = Source
      .fromURL(url)
      .getLines()
      .toList
      .flatMap(s => s.replaceAll("><", ">\n<").split("\n"))
    //a
    val location: List[String] = list take 10
    location map (println(_))
    list map (println(_))
    //b
    val xml = list.map(_ trim)
    val reg = ".*>(\\w+)</.*" r
    def getContent(tag: String) = xml filter (_ startsWith s"<$tag")
    val reg(city) = getContent("name") mkString ""
    val reg(country) = getContent("country") mkString ""
    println(city + "," + country)
    //c
    println(list filter (_ contains "</time>") size)
    //d
    def attribute(tag: String, attr: String) = {
      xml
        .filter(_ contains s"<$tag")
        .filter(_ contains s"$attr=")
        .map { s =>
          s.replaceAll(s""".*$attr="([^"]+)".*""", "$1")
        }
    }
    val times: List[String] = attribute("time ", "from")
    val symbols: List[String] = attribute("symbol ", "name")
    val forecast: List[(String, String)] = times zip symbols take 8
    println("12 hour forecast")
    forecast foreach {
      case (time, desc) =>
        val when = time.replaceAll("""T(\d+).*""", """ at $100""")
        println(s"$when | $desc")
    }
    //e
    println(symbols.distinct.sorted)
  }

}
