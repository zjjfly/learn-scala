package com.github.zjjfly.ls.chapt4

import scala.annotation.tailrec

/**
  * Created by zjjfly on 16/3/16.
  */
object exercise {
  def main(args: Array[String]) {
    println("areaOfCircle(2.3) = " + areaOfCircle(2.3))
    println("areaOfCircle(\"\") = " + areaOfCircle(""))
    printBy5(5, 50)
    println("get((\" 3 \",2)) = " + get(("3", 2)))
    println("get((1,2)) = " + get((1, 2)))
    println("addString((\" 13 \",1,true)) = " + addString(("13", 1, true)))
  }

  //1
  def areaOfCircle(radius: Double) = math.Pi * math.pow(radius, 2)

  //2
  def areaOfCircle(radius: String): Double = {
    radius isEmpty match {
      case true  => 0
      case false => math.Pi * math.pow(radius toDouble, 2)
    }
  }

  //3
  @tailrec
  def printBy5(i: Int, max: Int): Unit = {
    if (i <= max) {
      println(i)
      printBy5(i + 5, max)
    }
  }

  //4 答案:Long
  //5 见Functions.scala
  //6 了解tuple作为参数怎么声明
  def offset(src: (Int, Int), dest: (Int, Int)): (Int, Int) = {
    (src._1 - dest._1, src._2 - dest._2)
  }

  //7
  def get[A, B](t: (A, B)) = {
    def isInt(x: Any) = x.isInstanceOf[Int]
    (isInt(t._1), (isInt(t._2))) match {
      case (false, true) => (t._2, t._1)
      case _             => t
    }
  }
  //8
  def addString[A, B, C](t: (A, B, C)): (A, String, B, String, C, String) = {
    (t._1, t._1 toString, t._2, t._2 toString, t._3, t._3 toString)
  }
}
