package com.github.zjjfly.ls.chapt5

import java.util.UUID

import scala.util.Random

/**
  * 函数字面量块
  * Created by zjjfly on 16/3/22.
  */
object FuncLiteralBlock {
  def safeStringOp(s: String)(f: String => String) = {
    if (s != null) f(s) else s
  }

  //把by-named parameter和函数字面量块结合起来
  def timer[A](f: => A): A = {
    def now = System.currentTimeMillis
    val start = now;
    val a = f;
    val end = now
    println(s"Executed in ${end - start} ms")
    a
  }

  def main(args: Array[String]) {
    val uuid = UUID.randomUUID().toString
    //直接传入函数字面量块
    val timedUUID = HighOrderFunc.safeStringOp(uuid, { s =>
      val now = System currentTimeMillis
      val timed = s.take(24) + now
      timed toUpperCase
    })
    println("timedUUID = " + timedUUID)
    //也可以把两个参数分成两个参数组,这样看着更清晰
    val timeduuID = safeStringOp(uuid) { s =>
      val now = System currentTimeMillis;
      println(now)
      val timed = s.take(24) + now
      timed toUpperCase
    }
    println("timeduuID = " + timeduuID)

    val veryRandomAmount = timer {
      Random.setSeed(System currentTimeMillis)
      for (i <- 1 to 100000) Random.nextDouble;
      Random nextDouble;
    }
    println("veryRandomAmount = " + veryRandomAmount)
  }
}
