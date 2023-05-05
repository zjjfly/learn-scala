package com.github.zjjfly.ls.chapt6

import scala.collection.immutable.IndexedSeq
import scala.language.postfixOps

/**
  * Created by zjjfly on 16/3/29.
  */
object Exercise {
  def main(args: Array[String]) {
    val list = (1l to 20l) toList;
    //1 用for循环实现
    println(System.currentTimeMillis())
    val odds1: IndexedSeq[Long] = for (i <- 0l to 9l; j = i * 2 + 1) yield j
    println(System.currentTimeMillis())
    println("odds1 = " + odds1)
    //另一种for实现
    println(for (i <- 0l to 20l; if (i % 2 == 1)) yield i)
    //用filter
    println(System.currentTimeMillis())
    val odds2: List[Long] = list filter (_ % 2 == 1)
    println(System.currentTimeMillis())
    println("odds2 = " + odds2)
    //用map
    println(System.currentTimeMillis())
    val odds3: IndexedSeq[Long] = 0l to 9l map (_ * 2 + 1)
    println(System.currentTimeMillis())
    println("odds3 = " + odds3)
    //用collect
    println((0l to 20l) collect { case x if (x % 2 == 1) => x })

    //2
    //用flatenmap最简便
    val factor1: IndexedSeq[Long] = odds1 flatMap factors
    println("factor1 = " + factor1)
    //用for循环
    val factor2: IndexedSeq[Long] =
      (for (i <- odds1; j = factors(i)) yield j).flatten
    println("factor2 = " + factor2)

    //3
    //for循环实现
    def first1[A](items: List[A], count: Int): List[A] = {
      val re: IndexedSeq[A] = for (i <- 0 to count - 1) yield items(i)
      re.toList
    }
    println("first1(odds1,2) = " + first1(odds1.toList, 2))
    println("first1(odds1,5) = " + first1(odds1.toList, 5))
    //用take实现
    def first2[A](items: List[A], count: Int): List[A] = items take count
    println("first2(odds1.toList,4) = " + first2(odds1.toList, 4))
    //用foldleft实现
    def first3[A](items: List[A], count: Int): List[A] =
      items.foldLeft(List[A]()) { (a, i) =>
        if (a.size < count) a :+ i else a
      }
    println("first3(odds1.toList,2) = " + first3(odds1.toList, 2))
    //用递归实现
    def first4[A](items: List[A], count: Int): List[A] = {
      if (count > 0) items.head :: first4(items.tail, count - 1) else List[A]()
    }
    println("first4(odds1.toList,2) = " + first4(odds1.toList, 6))

    //4
    val strings = List("zjj", "sfaf", "wf", "jsj")
    //用fold实现
    def longest1(items: List[String]): String = {
      items.fold("") { (a: String, i: String) =>
        if (a.size < i.size) i else a
      }
    }
    println("longest1(strings) = " + longest1(strings))
    //reduce实现
    def longest2(items: List[String]): String =
      items.reduce((a, b) => if (a.size < b.size) b else a)
    println("longest2(strings) = " + longest2(strings))

    //5
    def reverse[A](items: List[A]): List[A] = {
      items match {
        //这种实现性能不好
        case x :: xs => reverse(xs) :+ x
        case Nil     => items
      }
    }
    println("reverse(strings) = " + reverse(strings))
    //6
    def isPalindrome(s: String): Boolean = s == s.reverse
    //partition实现
    val palindrome
      : (List[String], List[String]) = strings partition isPalindrome
    println("palindrome = " + palindrome)
    //自己实现
    def splitPallies(l: List[String]) = {
      l.foldLeft((List[String](), List[String]())) { (a, i) =>
        if (i == i.reverse) (i :: a._1, a._2) else (a._1, i :: a._2)
      }
    }
    println("palindrome = " + splitPallies(strings))

  }

  //2
  def factors(i: Long) = {
    2l to i - 1 filter (i % _ == 0)
  }
}
