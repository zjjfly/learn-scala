package com.github.zjjfly.ls.chapt7

import java.io.File

import scala.annotation.tailrec
import scala.collection.mutable
import scala.util.{Failure, Success, Try}
import scala.language.postfixOps

/**
  * Created by zjjfly on 16/4/6.
  */
object Exercise {
  def main(args: Array[String]) {
    //1 a) 用buffer
    def getSomeFib(x: Int): mutable.Buffer[Int] = {
      val fibonacci = mutable.Buffer(1)
      for (i <- 1 until x)
        fibonacci += fibonacci.takeRight(2).sum

      fibonacci
    }
    val fib: mutable.Buffer[Int] = getSomeFib(7)
    println("fib = " + fib)
    //builder 在这里不合适
    @tailrec
    def getSomeFibo(b: mutable.Builder[Int, List[Int]], n: Int): List[Int] = {
      if (b.result().size >= n) b.result()
      else getSomeFibo(b += (b.result().takeRight(2).sum), n)
    }
    val origin = List.newBuilder[Int]
    origin ++= List(1, 1)
    val someFibo: List[Int] = getSomeFibo(origin, 8)
    println(someFibo)

    //b) 可变集合实现
    def addSomeFibo(f: List[Int], n: Int): List[Int] = {
      val buffer = f.toBuffer
      for (i <- 1 to n) {
        buffer += (buffer takeRight (2) sum)
      }
      buffer.toList
    }
    println(addSomeFibo(List(1), 4))
    println(addSomeFibo(List(1, 1, 2, 3), 5))
    //不可变集合实现
    @tailrec
    def fibAdd(l: List[Int], count: Int): List[Int] = {
      if (count < 1) l
      else {
        val k = l :+ l.takeRight(2).sum
        fibAdd(k, count - 1)
      }
    }
    println(fibAdd(List(1), 3))
    println(fibAdd(List(1, 1), 9))
    //c)
    def fibStream(i: Long, j: Long): Stream[Long] =
      Stream.cons(i, fibStream(j, i + j))
    val list: List[Long] = fibStream(1, 1).take(100).toList
    list grouped 10 map (_.mkString(",")) foreach println
    //d)
    val fStream = fibStream(1, 1)
    def fibNext(i: Int): Option[Long] = {
      val preceeding = fStream.takeWhile(_ <= i).toList
      if (preceeding.last == i) Some(preceeding.takeRight(2).sum)
      else None
    }
    println(fibNext(8))

    //2
    def listFile(path: String): Array[String] = {
      val files: Array[File] = new File(path).listFiles
      files.map(_.toString.replaceFirst("./", ""))
    }
    listFile(".") foreach println
    val files: Array[String] = listFile(".") filterNot (_ startsWith ".")
    println(files mkString ";")
    //3
    val head2files: List[(Char, Array[String])] =
      files.groupBy(_.head.toLower).toList.sortBy(_._1)
    head2files map (s => s._1 + " has " + s._2.size + " files") foreach println
    //4
    def toDouble(x: String): Option[Double] = Try(x toDouble).toOption
    //使用模式匹配
    def product1(x: String, y: String): Option[Double] = {
      (toDouble(x), toDouble(y)) match {
        case (Some(a), Some(b)) => Option(a * b)
        case _                  => None
      }
    }
    println(product1("1.3", "4.0"))
    println(product1("a", "1"))
    //使用for循环
    def product2(x: String, y: String): Option[Double] = {
      for (a <- toDouble(x); b <- toDouble(y)) yield a * b
    }
    println(product2("1.3", "4.0"))
    println(product2("a", "1"))

    //5
    def getProperty(x: String): Option[String] = {
      Try(System getProperty x) match {
        case Success(x)  => Option(x)
        case Failure(ex) => None
      }
    }
    println(getProperty(null))
    println(getProperty(""))
    println(getProperty("blah"))
    println(getProperty("java.home"))
  }
}
