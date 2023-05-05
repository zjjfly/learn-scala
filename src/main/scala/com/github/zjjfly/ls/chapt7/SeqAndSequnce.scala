package com.github.zjjfly.ls.chapt7

import scala.language.postfixOps
/**
  * Created by zjjfly on 16/4/4.
  */
object SeqAndSequnce {
  def main(args: Array[String]) {
    //Seq是所有的链表list和indexed list的根类
    //Seq不能被实例化,但可以作为产生一个list对象的捷径
    val inks: Seq[String] = Seq("c", "m", "y")
    println("inks = " + inks)
    //一下是Seq及它的子类的目录结构
    //Seq  The root of indexed sequences. Shortcut forVector().
    //IndexedSeq   The root of indexed sequences. Shortcut for Vector(). A list backed by anArrayinstance for indexed access. A range of integers. Generates its data on-the-fly.
    //Vector  A list backed by an Array instance for indexed access.
    //Range   A range of integers. Generates its data on-the-fly.
    //LinearSeq  The root of linear (linked - list) sequences.
    //List  A singly linked list of elements.
    //Queue  A first -in - last - out(FIFO) list.
    //Stack  A last -in - first - out(LIFO) list.
    //Stream  A lazy list.Elements are added as they are accessed.
    //String  A collection of characters.

    //Vector是用数组存储的一种实现,类似java里的ArrayList
    //Seq和IndexedSeq分别是List和Vector的快捷表示,但一般不推荐这么用

    //String也是一种Sequence,继承了Iterable并支持它的操作,它同时也是java的String类的封装类,支持split和trim等方法
    val hi = "Hello," ++ "wordly" take 12 replaceAll ("w", "W")
    println("hi = " + hi)

    //Stream,类似clojure的lazy sequence,和其他在初始化的时候接收所以的元素的不可变集合不一样.
    //一个stream的元素是被缓存起来以备后用,确保每一个元素只生成一次.Stream理论上可以是无限长的
    //Stream可以被Stream.Empty终止,和List.Nil对应
    //Stream使用一个头元素和一个函数的递归调用初始化
    def inc(i: Int): LazyList[Int] = {
      println("i=" + i)
      LazyList.cons(i, inc(i + 1))
    }
    val s = inc(1)
    println("s = " + s)
    val l = s.take(5).toList
    println("l = " + l)
    //还有一种产生Stream的方法是用#::
    def increase(head: Int): LazyList[Int] = head #:: increase(head + 1)
    println(increase(10) take 10 toList)

    //生产一个有限的Stream,用带两个参数的递归函数,一个起始值,一个终止值
    def to(head: Char, end: Char): LazyList[Char] = (head > end) match {
      case true  => LazyList.empty //Stream.Empty终止Stream
      case false => head #:: to((head + 1).toChar, end)
    }
    val hexChars = to('a', 'f').take(20).toList
    println("hexChars = " + hexChars)

  }
}
