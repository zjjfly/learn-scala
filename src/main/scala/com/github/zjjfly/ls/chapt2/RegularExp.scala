package com.github.zjjfly.ls.chapt2

/**
  * Created by zjjfly on 16/3/10.
  */
object RegularExp {
  def main(args: Array[String]) {
    //string中和正则有关的用法
    val s = "Froggy went a' courting"
    val b = s matches ".*courting"
    println(b)
    val drink = "milk, tea, muck"
    val s1: String = drink replaceAll ("m[^ ]+k", "coffee")
    println("s1 = " + s1)
    val s2: String = drink replaceFirst ("m[^ ]+k", "coffee")
    println("s1 = " + s2)
    //string用r把自身转换成一个Regex对象
    //用三引号修饰的字符串只需用一个反斜杠表示转义
    val regex = """.* apple ([\d.]+) (\w+) .*""" r
    val input = "Enjoying this apple 3.14159 today "
    //用正则匹配input,把正则中的匹配括号的按次序赋值给amount,d变量
    val regex(amount1, d) = input
    println(amount1 + d)
    val f: Boolean = true & false;
  }
}
