package com.github.zjjfly.ls.chapt7

import java.io.{FileNotFoundException, FileInputStream, File}

import scala.util.{Success, Try, Failure}
import scala.language.postfixOps

/**
  * 一元集合
  * Created by zjjfly on 16/4/5.
  */
object MonadicCollection {
  def main(args: Array[String]) {
    //Option,用法类似java8里的Option
    var x: String = "Indeed"
    val a = Option(x)
    println("a = " + a)
    x = null
    val b = Option(x)
    println("b = " + b)
    //用isDefined和isEmpty来判断是否有值,是否是空的
    println(s"a is defined? ${a.isDefined}")
    println(s"b is empty? ${b.isEmpty}")

    //一个更接近实际应用的例子
    def divide(amt: Double, divisor: Double): Option[Double] =
      if (divisor == 0) None else Some(amt / divisor)
    val legit = divide(2, 5)
    println("legit = " + legit)
    println("legit.get = " + legit.get)
    println(divide(2, 0))
    //Option常用于作为函数的返回值,它是类型安全的
    //比如,对于空集合,调用head方法会报错,调用headOption不会
    val list = List()
    println("list = " + list.headOption)
    //Option的另一个用处是find操作,它是filter和headOption的组合,找到符合条件的第一个元素
    val words = List("risible", "scavenger", "gist")
    val lowcase: Option[String] = words find (w => w == w.toLowerCase)
    println("lowcase = " + lowcase)
    println(lowcase isEmpty)
    //对空的Option做filter,map等操作不会报空指针异常
    val filtered
      : Option[String] = lowcase filter (_ endsWith "ible") map (_ toUpperCase)
    val exactSize: Option[Int] = filtered filter (_.size > 15) map (_.size)
    println("exactSize = " + exactSize)

    //用get方法取Option里的值不安全,因为如果Option是空的,会报错
    //推荐的几种方法:
    //用fold,foldLeft,foldRight或reduceXXX
    def nextOption = if (util.Random.nextInt > 0) Some(1) else None
    val c = nextOption
    val d = nextOption
    val cVal: Int = c.fold(-1)(x => x)
    //用getOrElse,它的第二个参数是一个by-name Parameter
    val dVal1: Int = d getOrElse 5
    val dVal2: Int = d getOrElse {
      println("error!")
      -1
    }
    println("cVal = " + cVal)
    println("dVal1 = " + dVal1)
    println("dVal2 = " + dVal2)
    //orElse,如果Option不会空,则返回该Option,否则返回另一个Option,它的参数也是by-name Parameter
    println(c orElse d)
    //或者用match模式匹配,解构出Option的值

    val e = legit match {
      case Some(x) => x
      case _       => -1
    }
    println("e = " + e)
    //scala也支持try catch错误处理
    //但不推荐这么做
    try {
      val file = new File("dad")
      val input = new FileInputStream(file)
      input.read(new Array[Byte](1000))
    } catch {
      case ex: FileNotFoundException => ex.printStackTrace
    }
    //另一种Monadic集合是try,它的参数是对一个函数的调用,调用产生的异常或者返回值会被放入try
    //它主要作用是可以防止程序因为错误中断
    def loopAndFail(end: Int, failAt: Int): Int = {
      for (i <- 1 to end) {
        println(s"$i) ")
        if (i == failAt) throw new Exception("Too many iterations")
      }
      end
    }
    //try有两个子类,Success和Failure,分别对应了成功的返回和失败的返回
    val t1 = Try(loopAndFail(2, 3))
    //也可以用表达式块
    val t3 = Try {
      loopAndFail(4, 2)
    }
    println("t1 = " + t1)
    val t2 = Try(loopAndFail(10, 3))
    println("t2 = " + t2)
    //try的一些操作
    def nextError = Try {
      1 / util.Random.nextInt(2)
    }
    val y = nextError
    val z = nextError
    //flatMap 如果是Failure,直接返回
    val triedInt: Try[Int] = y flatMap (s => Try(1 / s))
    println("triedInt = " + triedInt)
    //foreach,当时Success的时候执行
    z foreach (s => println("z=" + s))
    //getOrElse,当时Success的时候返回Try里的值,否则返回一个Failure
    println("z getOrElse 0=" + (z getOrElse 0))
    //orElse
    println("y orElse nextError=" + (y orElse nextError))
    //toOption,转换成Option
    println("z.toOption=" + z.toOption)
    //map,如果是Success才调用
    println("y map (_*2)=" + (y map (_ * 2)))
    //模式匹配,可以获取Success里的值和Failure里的Exception
    z match {
      case Success(x)  => println(x)
      case Failure(ex) => ex.printStackTrace
    }
    //Try可以用来判断一个字符串是否可以转换成数字
    val in = " 123 "
    val result = Try(in.toInt) orElse Try(in.trim.toInt)
    result foreach { r =>
      println(s"Parsed '$in' to $result!")
    }
    val xx = result match {
      case Success(x) => Some(x)
      case Failure(ex) => {
        println(s"Couldn't parse input '$in'")
        None
      }
    }
    println("xx = " + xx)
    //具体使用什么处理异常的方式取决于环境和需求,但要避免忽视发现的异常
  }
}
