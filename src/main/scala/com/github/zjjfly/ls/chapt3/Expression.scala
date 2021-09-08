package com.github.zjjfly.ls.chapt3

/**
  * scala的表达式
  * Created by zjjfly on 16/3/11.
  */
object Expression {
  def main(args: Array[String]) {
    //字面量就是一种最简单的表达式
    val n = 1
    //计算式子也是一种表达式
    val m = 1 + 1
    //可以多个语句组成一个表达式块,用{}包括
    val amount = { val x = 5 * 20; x + 10 }
    println("amount = " + amount)
    //可以写成多行的形式,这样分号可以去除
    val a = {
      val x = 5 * 20
      x + 10
    }
    println("a = " + a)
    //表达式块可以嵌套
    val c = { val a = 1; { val b = a * 2; { val c = b + 4; c } } }
    println("c = " + c)
    //声明语句返回Unit

    //if语句
    if (47 % 3 > 0) println("Not a multiple of 3")
    //如果是if后的表达式是false,则返回的类型是?
    val result = if (false) 1
    println("x is Any type? " + result.isInstanceOf[Any])
    //if else语句
    val x = 10
    val y = 20
    val max = if (x > y) x else y
    println("max = " + max)
  }
}
