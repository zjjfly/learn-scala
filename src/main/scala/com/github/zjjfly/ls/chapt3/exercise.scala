package com.github.zjjfly.ls.chapt3

import scala.language.postfixOps
/**
  * Created by zjjfly on 16/3/14.
  */
object exercise {
  def main(args: Array[String]) {
    //1
    val name = "da"
    val re1 = name match {
      case n if n != null && !(n.asInstanceOf[String] isEmpty) =>
        n
      case _ =>
        "n/a"
    }
    println(re1)
    //2
    var amout = -2.2
    //if表达式
    val re2 = if (amout > 0) {
      "greater"
    } else {
      if (amout == 0) {
        "same"
      } else {
        "less"
      }
    }
    println(re2)
    //match
    amout = 1.2
    val re3 = amout match {
      case d if (d > 0) =>
        "greater"
      case d if (d == 0) =>
        "same"
      case d =>
        "less"
    }
    println(re3)
    //3
    val input = "cyan"
    val re4 = input match {
      case "cyan"    => "00FFFF"
      case "magenta" => "FF00FF"
      case "yellow"  => "FFFF00"
      case _ =>
        println(s"Din't expect $input")
        "N/A"
    }
    println(re4)
    //4
    for (i <- 1 to 100) {
      print(i)
      i % 5 match {
        case 0 =>
          print(",\n")
        case other =>
          print(",")
      }
    }
    //5
    for (i <- 1 to 100) {
      i match {
        case x if (x % 15) == 0 =>
          println("typesafe")
        case x if (x % 5) == 0 =>
          println("safe")
        case x if (x % 3) == 0 =>
          println("type")
        case _ =>
          println(i)
      }
    }
//   6
    for (i <- 1 to 100) {
      var s = ""; if (i % 3 == 0) s += "type"; if (i % 5 == 0) s += "safe";
      if (s isEmpty) s += i; println(s)
    }
  }
}
