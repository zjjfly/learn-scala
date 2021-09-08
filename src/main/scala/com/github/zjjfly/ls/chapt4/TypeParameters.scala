package com.github.zjjfly.ls.chapt4

/**
  * Created by zjjfly on 16/3/16.
  */
//类型参数,作用是使函数更灵活,从固定的参数类型,返回值类型变为由调用函数的时候确定类型
object TypeParameters {
  def main(args: Array[String]) {
    //下面的代码会报错,因为Any没办法转成String
    //    val s:String=identity3("hello")
    println(identity("12"))
    println(identity(1))
  }

  //定义一个参数,返回传入的参数
  def identity1(s: String): String = s

  //这样定义会只能传入字符串,如果要int型的,需要重新定义一个函数
  def identity2(i: Int): Int = i

  //但如果又需要别的类型,则需要定义很多相似的函数

  //那如果我定义成Any呢?
  def identity3(a: Any): Any = a

  //显然也有问题

  //解决方法,用类型参数,scala中一般用大写A作为类型参数名,java中是T
  def identity[A](a: A): A = a
}
