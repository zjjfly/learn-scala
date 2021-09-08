package com.github.zjjfly.ls.chapt5

/**
  * Created by zjjfly on 16/3/16.
  */
object HighOrderFunc {
  def main(args: Array[String]) {
    println("safeStringOp(null,reverser()) = " + safeStringOp(null, reverser))
    println(
      "safeStringOp(\"zjj\",reverser()) = " + safeStringOp("zjj", reverser))
    //还有一种传入函数参数的方法是用函数字面量,详见FunctionLiteral.scala
  }

  //参数是函数的函数
  def safeStringOp(s: String, f: String => String): String = {
    if (s != null) f(s) else s
  }

  def reverser(s: String) = s.reverse
}
