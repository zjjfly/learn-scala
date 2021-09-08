package com.github.zjjfly.ls.chapt6

/**
  * Created by zjjfly on 16/3/28.
  */
object PattenMatch {
  def main(args: Array[String]) {
    val statuses = List(500, 404)
    //匹配某个元素
    val msg = statuses.head match {
      case x if x < 500 => "okay"
      case _            => "error"
    }
    //直接匹配list
    println("msg = " + msg)
    val message = statuses match {
      case x if (x contains (404)) && (x contains (500)) => "error"
      case _                                             => "okay"
    }
    //另一种匹配list
    println("message = " + message)
    val m = statuses match {
      case List(404, 500) => "not found error"
      case List(500, 404) => "error not found"
      case List(200, 200) => "ok"
      case _              => "not sure"
    }
    println("m = " + m)
    //匹配 绑定数据
    val msg1 = statuses match {
      case List(500, x) => s"Error followed by $x"
      case List(e, x)   => s"$e was followed by $x"
    }
    println("msg1 = " + msg1)
    //匹配list头和尾
    val head = List('r', 'g', 'b') match {
      case x :: xs => x + "\ntail=" + xs
      case Nil     => ' '
    }
    println("head = " + head)

    //tuple也支持模式匹配和数据绑定
    val code = ('h', 204, true) match {
      case (_, _, false)  => 501
      case ('c', _, true) => 302
      case (c, x, true)   => println(s"Did not expect code $c"); x
    }
    println("code = " + code)
  }
}
