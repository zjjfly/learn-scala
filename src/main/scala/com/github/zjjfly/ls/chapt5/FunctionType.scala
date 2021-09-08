package com.github.zjjfly.ls.chapt5

/**
  * Created by zjjfly on 16/3/16.
  */
object FunctionType {
  //函数是头等公民,指函数不止用来调用,还可以作为参数,作为字面量,作为返回值

  //以函数为参数且/或返回值是函数的函数被称为高阶函数
  //最知名的高阶函数是map和reduce

  def double(x: Int) = x * 2

  def max(a: Int, b: Int) = if (a > b) a else b

  def logStart() = "=" * 50 + "\nStarting NOW\n" + "=" * 50

  def main(args: Array[String]) {
    println("double(2) = " + double(2))
    //函数的类型,只需要指明函数的参数类型和返回值类型就可以,不需要函数名
    val myDouble: (Int) => Int = double
    //(Int)的括号可以省略,因为只有一个参数
    //声明myDouble需要要指明类型,不然会报错,如果不指明,可以用_
    val myDouble1 = double _
    //double _实际上是返回了一个函数
    println("myDouble = " + myDouble(3))
    println("myDouble = " + myDouble1(4))

    //多参数的函数
    val maximize: (Int, Int) => Int = max
    println("maximize = " + maximize(1, 3))

    //无参数的函数
    val start: () => String = logStart
    println(start())
  }

}
