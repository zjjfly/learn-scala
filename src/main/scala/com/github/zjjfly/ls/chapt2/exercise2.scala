package com.github.zjjfly.ls.chapt2

/**
  * Learning Scala的第二章习题
  * Created by zjjfly on 16/3/11.
  */
object exercise2 {
  def main(args: Array[String]) {
    //1
    val centigrade = 21.4
    val a = 9 / 5.0
    val fahrenheit = centigrade * a + 32
    println("fahrenheit = " + fahrenheit)
    //2
    println("fahrenheit.toInt = " + fahrenheit.toInt)
    //3
    val amount = 2.7255
    println(f"You owe $$$amount%.2f dollars")
    //4
    val i = 128
    val char: Char = i.toChar
    println("char = " + char)
    println("char.toInt = " + char.toInt)
    val d = i.toDouble
    println("d = " + d)
    println("d.toInt = " + d.toInt)
    //5
    val s = "Frank,123 Main,925-555-1943,95122"
    val reg = """.*(\d{3})-(\d{3})-(\d{4}).*""" r
    val reg(p1, p2, p3) = s
    println("p1 = " + p1)
    println("p2 = " + p2)
    println("p3 = " + p3)
  }
}
