package com.github.zjjfly.ls.chapt10

import java.util

/**
  * Created by zjjfly on 16/5/6.
  */
object ScalaTypeFeature {
  def main(args: Array[String]) {
    //(x,y)这种生成tuple的语法的实质
    val t1: (Int, Char) = (1, 'a')
    val t2: (Int, Char) = Tuple2[Int, Char](1, 'a')
    println(t1 == t2)
    //函数字面量的实质
    val f1: Int => Int = _ + 2
    val f2: Int => Int = new Function1[Int, Int] { def apply(x: Int) = x + 2 }
    println(f1(2) == f2(2))
    ImplicitClasses.test
    ImplicitParams.test

    //协变
    val l: List[Base] = List[Sub]()

  }
  def increment[b <: Base](b: Base) = { b.i += 1; b }
}

object ImplicitClasses {
  //implicit class,用来给现有的类加入方法或成员
  //string类型调用hello方法实质是先转换成Hello类型,然后再调用Hello类的hello方法
  implicit class Hello(s: String) {
    def hello = s"Hello, $s"
  }

  implicit class MyList[A](s: util.ArrayList[A]) {
    def count = s.stream().count()
  }
  def test = {
    println("World".hello)
    val strings = new util.ArrayList[String]() { add("faf"); add("afaf") }
    println(strings.count)
  }
}

object ImplicitParams {

  //定义隐式参数,如果同一命名空间中定义了有一个implicit修饰的变量,会直接使用这个变量作为参数,也可以显示的传入参数
  def greet(name: String)(implicit greeting: String) = s"$greeting, $name"
  //
  implicit val hi = "Hello"

  def test = {
    println(greet("Developers"))
  }
}

//upper bound
class Base { var i = 10 }

class Sub extends Base
