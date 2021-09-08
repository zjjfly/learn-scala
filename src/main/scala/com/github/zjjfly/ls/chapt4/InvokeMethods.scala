package com.github.zjjfly.ls.chapt4

/**
  * Created by zjjfly on 16/3/16.
  */
object InvokeMethods {
  def main(args: Array[String]) {
    //中缀操作符,实际是调用函数,scala中实际没有操作符
    println("1.2+2.3 = " + 1.2 + 2.3)
    //scala会把这个+号变为对+()函数的调用
    //这种语法一般用于单参数函数的调用,如果有多参数,可以把多个参数yong()包括
    println("dasfaf" substring (1, 2))

    //如果一个表达式有多个操作符,其实也是函数调用,把第一个操作符的变为函数调用,再用函数的返回结果去调用下一个操作符对应的函数
    1 + 2 + 3
    //=(1.+(2)).+(3)
    //scala支持中缀操作符语法,可以简化语法,也鼓励程序员写单参数的函数
    //代码可读性也更好
    //但是过多使用,如一个表达式用了七八个操作符函数,会搞不清操作符和操作数
    //所以使用起来也要谨慎
  }
}
