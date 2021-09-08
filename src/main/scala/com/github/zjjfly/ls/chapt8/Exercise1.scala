package com.github.zjjfly.ls.chapt8

import java.text.SimpleDateFormat
import java.util.Date

/**
  * Created by zjjfly on 16/4/16.
  */
object Exercise1 {
  def main(args: Array[String]) {
    //1
    //a)
    new GameShop().reportGames()
  }
}

abstract class MediaFormat

class DvdMediaFormat extends MediaFormat

class BluRayMediaFormat extends MediaFormat

class USBMediaFormat extends MediaFormat

class CartridgeMediaFormat extends MediaFormat

abstract class VideoResolution(pixels: Int)

class HD extends VideoResolution(1280 * 720)

class FHD extends VideoResolution(1920 * 1080)

class QHD extends VideoResolution(2560 * 1440)

class UHD4K extends VideoResolution(3840 * 2160)

class GameConsole(val make: String,
                  val model: String,
                  val debut: Date,
                  val wifiType: Option[String],
                  val mediaFormats: List[MediaFormat],
                  val maxVideoResolution: VideoResolution) {

  override def toString =
    s"GameConsole($make, $model), max video resolution = ${maxVideoResolution.getClass.getName}"
}

class GameConsoleLibrary {

  def strToDate(s: String): Date =
    new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(s)

  val chanduOne = new GameConsole("Chandu",
                                  "One",
                                  strToDate("2007-02-01 19:00"),
                                  Some("a/b"),
                                  List(new CartridgeMediaFormat),
                                  new HD)

  val aquaTopia = new GameConsole("Aqua",
                                  "Topia",
                                  strToDate("2012-05-02 00:00"),
                                  Some("a/b/g"),
                                  List(new DvdMediaFormat),
                                  new HD)

  val oonaSeven = new GameConsole(
    "Oona",
    "Seven",
    strToDate("2014-03-03 00:00"),
    Some("b/g/n"),
    List(new BluRayMediaFormat, new DvdMediaFormat),
    new FHD)

  val leoChallenge = new GameConsole("Leonardo",
                                     "Challenge",
                                     strToDate("2014-12-12 00:00"),
                                     Some("g/n"),
                                     List(new USBMediaFormat),
                                     new UHD4K)

}

class Game(val name: String,
           val maker: String,
           val consoles: List[GameConsole]) {
  def isSupported(console: GameConsole) = consoles contains console

  override def toString = s"Game($name, by $maker)"
}

class GameShop {

  val consoles = new GameConsoleLibrary()

  val games = List(
    new Game("Elevator Action", "Taito", List(consoles.chanduOne)),
    new Game("Mappy", "Namco", List(consoles.chanduOne, consoles.aquaTopia)),
    new Game("StreetFigher",
             "Capcom",
             List(consoles.oonaSeven, consoles.leoChallenge))
  )

  val consoleToGames: Map[GameConsole, List[Game]] = {
    val consoleToGames1: List[(GameConsole, Game)] = games flatMap (g =>
      g.consoles.map(c => c -> g))
    val consoleToGames2
      : Map[GameConsole, List[(GameConsole, Game)]] = consoleToGames1 groupBy (_._1)
    val consoleToGames3
      : Map[GameConsole, List[Game]] = consoleToGames2 mapValues (_ map (_._2))
    consoleToGames3
  }

  def reportGames(): Unit = {
    games sortBy (g => s"${g.maker}_${g.name}") foreach { game =>
      val consoleInfo =
        game.consoles.map(c => s"${c.make} ${c.model}").mkString(", ")
      println(s"${game.name} by ${game.maker} for $consoleInfo")
    }
  }
}
