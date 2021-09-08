package com.github.zjjfly.ls.chapt4

import scala.annotation.tailrec

/**
  * Created by zjjfly on 16/3/16.
  */
object Functions {
  //最简单的函数定义,无输入函数,也不定义返回值类型,靠编译器的类型推断
  def hi = "Hi"

  //为了和变量区分,最好在函数名后面加括号,即使它没有参数
  def hi2() = "Hi"

  //调用hi2可以跟()也可以省略,但调用hi不能加()
  //scala的惯例是如果这个无参函数有副作用,就要加()

  //一个比较完整的函数定义
  def mutiplier(x: Int, y: Int): Int = {
    x * y
  }

  //用return提前退出函数
  def safeTrim(s: String): String = {
    //    require(s!=null)
    if (s == null) return null
    s.trim
  }

  //procedure,返回值是Unit的函数
  def log(d: Double) = println(f"got value $d%.2f")

  //有另一种不是很推荐的定义procedure的方法,就是省略等号,函数体用花括号括起来
  def log2(d: Double) {
    println(f"got value $d%.2f")
    1
  }

  //不推荐这样做的原因是,很多程序员写的procedure意外的有返回值,他们也希望得到返回值,这样写返回值会被丢弃,仍返回Unit

  //递归函数
  //它可以实现————不使用可变对象的遍历
  //指数计算
  def power(x: Int, n: Int): Long = {
    if (n >= 1) x * power(x, n - 1)
    else 1
  }

  //递归函数容易出现内存溢出问题,因为每次调用都会分配一个内存栈,scala中可以使用尾递归优化避免这个问题.
  //如果一个函数中所有递归形式的调用都出现在函数的末尾，我们称这个递归函数是尾递归的
  //把上面的函数尾递归化
  def power2(x: Int, n: Int): Long = {
    if (n < 1) 1
    else x * power2(x, n - 1)
  }

  //用注解告诉编译器对函数进行尾递归优化,如果函数不是尾递归的,会报错
  //  @tailrec
  //  def power3(x:Int,n:Int):Long={
  //    if (n<1)  1
  //    else  x*power3(x,n-1)
  //  }
  //上面这个函数还是报错,因为实际上最后一个表达式不是对自身的调用,而是一个乘法表达式
  //再次修改
  @tailrec
  def power3(x: Int, n: Int, t: Long = 1): Long = {
    if (n < 1) t
    else power3(x, n - 1, x * t)
  }

  //嵌套函数,如果一个函数只被另一个函数使用,可以定义在那个函数内部
  def max(a: Int, b: Int, c: Int) = {
    //内部函数的名字和参数列表可以完全和包含它的函数一样
    def max(x: Int, y: Int) = if (x > y) x else y
    max(a, max(b, c))
  }

  def formatEuro(amt: Double) = f"€$amt%.2f"

  def greet(prefix: String, name: String) = s"$prefix $name"

  //函数的默认参数
  //这样可以避免过多的重载,这个特性python也有
  def greet2(prefix: String = "", name: String) = s"$prefix$name"

  //上面这样定义需要在调用的时候指明传入的参数名
  //如果想要不指明参数,把有默认值的参数放在一般参数的后面
  def greet3(name: String, prefix: String = "") = s"$prefix$name"

  //不定参数的函数
  //在参数的类型后加*号,表示不定参数,它后面不能再有一般的参数
  def sum(items: Int*): Int = {
    var total = 0
    for (i <- items) total += i
    total
  }

  //参数组
  //主要在高阶函数会用到,给下面的函数传入一个参数,会返回一个函数
  def max(x: Int)(y: Int) = if (x > y) x else y

  def main(args: Array[String]) {
    println(hi)
    println("mutiplier(4,5) = " + mutiplier(4, 5))
    //函数的函数体实际上是由表达式或表达式块组成的
    println("safeTrim(\"ad\") = " + safeTrim(null))
    log(2.13)
    val x = log2(3.3131)
    println(x)
    println("hi2 = " + hi2)
    //如果函数只有一个参数,可以使用{}代替()调用函数,{}里可以是一个语句块,最后的返回值作为参数传入函数
    //如果传入的参数需要进过计算,而计算产生的中间结果在其他地方没有用处,这种用法可以避免产生过多的中间变量
    println(formatEuro(3.345))
    println(formatEuro {
      val rate = 1.32;
      0.235 + 0.7123 + rate * 5.32
    })
    println("power(2,8) = " + power(2, 8))
    println("power2(2,8) = " + power2(2, 8))
    println("power3(2,8) = " + power3(2, 32))
    println("max(1,2,3) = " + max(1, 2, 3))

    //使用named parameters调用函数,这样可以不用按函数声明的参数顺序传入参数
    //这个特性类似python
    println(greet("Ms", "Brown"))
    println(greet(name = "Brown", prefix = "Mr"))

    //调用有默认参数的函数
    println(greet2(name = "jjzi"))
    println(greet3("jjzi"))

    println("sum(2,3,5) = " + sum(2, 3, 5))
    println("sum() = " + sum())
    //有参数组的函数可以得到偏函数
    val maxPartial = max(1) _
    println("max(1) = " + maxPartial(2))
  }
}
