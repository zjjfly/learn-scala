package com.github.zjjfly.ls.chapt5

import scala.util.Random

/**
  * Created by zjjfly on 16/3/22.
  */
object exercise {

  //1
  def Max(t: (Int, Int, Int))(f: (Int, Int) => Int): Int = {
    f(t._1, f(t._2, t._3))
  }

  //3
  def productFunc(i: Int) = (x: Int) => i * x

  def main(args: Array[String]) {
    //1
    val max = (a: Int, b: Int) => if (a > b) a else b
    println("Max((1,4,5),max) = " + Max(10, 4, 5)(max))
    //2
    def nextInt = Random.nextInt
    println("max = " + max(nextInt, nextInt))
    val max1: Int = Max(nextInt, nextInt, nextInt)(max)
    println("max1 = " + max1)
    val max2: Int =
      Max(nextInt, nextInt, nextInt)((a, b) => if (a > b) b else a)
    println("max2 = " + max2)
    val max3: Int = Max(nextInt, nextInt, nextInt) { (a, b) =>
      b
    }
    println("max3 = " + max3)
    //3
    val triple: (Int) => Int = productFunc(3)
    println("function = " + triple(2))
    //5 题中的做法会报错,正确做法是:
    def square(m: Double) = m * m
    val sq = square _
    println("sq(2.1) = " + sq(2.1))
    val sql: Double => Double = square
    println("sql(1.1) = " + sql(1.2))
    //6
    val i: Int = conditional[Int](10) { case 10 => true; case _ => false } {
      Random.nextInt
    }
    println("i = " + i)

    //7
    //第一种做法
    //    for (i <- 1 to 100) {
    //      val a1 = conditional[Int](i)(_ % 3 == 0)(x => {
    //        print("type");
    //        0
    //      })
    //      val a2 = conditional[Int](i)(_ % 5 == 0)(x => {
    //        print("safe");
    //        0
    //      })
    //      if (a1 > 0 && a2 > 0) print(i)
    //      println("")
    //    }
    //第二种,去掉一些副作用
//    for (i <- 1 to 100) {
//      val a1 = conditional1[Int](i, _ % 3 == 0, _ => "type")
//      val a2 = conditional1[Int](i, _ % 5 == 0, _ => "safe")
//      val a3 = conditional1[Int](i, _ % 3 > 0 && i % 5 > 0, x => s"$x")
//      println(a1 + a2 + a3)
//    }
    //第三种,去掉println
    val seq = 1 to 100 map typeSafely
    println("seq = " + seq.mkString("\n"))
  }

  def conditional1[A](x: A, p: A => Boolean, f: A => String): String = {
    if (p(x)) f(x) else ""
  }

  //6
  def conditional[A](x: A)(p: A => Boolean)(f: A => A) = {
    if (p(x)) f(x) else x
  }

  def typeSafely(i: Int): String = {
    val a1 = conditional1[Int](i, _ % 3 == 0, _ => "type")
    val a2 = conditional1[Int](i, _ % 5 == 0, _ => "safe")
    val a3 = conditional1[Int](i, _ % 3 > 0 && i % 5 > 0, x => s"$x")
    a1 + a2 + a3
  }
}
