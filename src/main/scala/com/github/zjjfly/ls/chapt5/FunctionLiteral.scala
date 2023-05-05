package com.github.zjjfly.ls.chapt5

import scala.util.Random
import scala.language.postfixOps

/**
  * Created by zjjfly on 16/3/16.
  */
object FunctionLiteral {
  def main(args: Array[String]) {
    //用函数字面量给一个变量赋值
    val doubler = (x: Int) => 2 * x
    println("doubler(5) = " + (doubler(5)))
    //无参数的函数字面量
    val start = () => "=" * 50 + "\nStarting NOW\n" + "=" * 50
    println(start())
    val op: String = HighOrderFunc.safeStringOp("zjj", (s: String) => s.reverse)
    println("op = " + op)
    //由于safeStringOp已经声明了传入的函数的类型,所以函数字面量的参数可以不带类型
    val op1: String = HighOrderFunc.safeStringOp("cjl", s => s.reverse)
    println("op1 = " + op1)

    //还有一种更简单的语法,占位符语法,使用_
    //只有当函数的类型已经在外边被声明了,并且参数被使用的次数不超过一次
    val doubler1: Int => Int = _ * 2
    println("doubler1(3) = " + doubler1(3))
    println(HighOrderFunc.safeStringOp("ddc", _ reverse))
    //多参数函数字面量也可以用占位符语法
    //但如果占位符的个数和传入的参数不匹配,运行时会报错
    println(combination(5, 2, _ * _))
    //调用时要指明类型
    println(tripleOp[Int, Int](23, 13, 41, _ + _ * _))
    println(tripleOp[Int, Double](23, 13, 41, 1.0 * _ / _ / _))

    println("doubles(3) = " + doubles(3))
    //f(2)调用了两次
    println(doubles(f(2)))
    println(f(Random.nextInt()))
  }

  def combination(x: Int, y: Int, f: (Int, Int) => Int) = f(x, y)

  //可以和类型参数结合使用
  def tripleOp[A, B](a: A, b: A, c: A, f: (A, A, A) => B) = f(a, b, c)

  //by-named parameter,这种语法可以让某个参数可以传值,也可以传函数
  //但如果传入函数,在函数中的每次使用这个参数都会调用一次这个函数
  def doubles(x: => Int) = {
    println("Now doubling " + x)
    x * 2
  }

  def f(i: Int) = {
    println(s"Hello from f($i)");
    i
  }
}
