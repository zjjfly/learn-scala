package com.github.zjjfly.ls.chapt5

/**
  * 偏函数
  * Created by zjjfly on 16/3/16.
  */
object PartiallyFunc {
  //用占位符产生偏函数
  def main(args: Array[String]) {
    //之前提到过的一种用法,不保留任何参数
    val f = factorOf _
    println("f = " + f(2, 4))
    //保留一个参数,得到一个偏函数
    val multipleOf3 = factorOf(3, _: Int)
    println("multipleOf3(6) = " + multipleOf3(6))

    //更干净的一种产生偏函数的方法是用参数组,也就是currying function
    val isEven = factorOf1(2) _
    println("isEven = " + isEven(3))

  }
  def factorOf(x: Int, y: Int) = y % x == 0

  def factorOf1(x: Int)(y: Int) = y % x == 0

}
