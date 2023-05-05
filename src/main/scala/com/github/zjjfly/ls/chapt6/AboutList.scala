package com.github.zjjfly.ls.chapt6

import scala.annotation.tailrec
import scala.language.postfixOps

/**
  * Created by zjjfly on 16/3/25.
  */
object AboutList {
  def main(args: Array[String]) {
    //list中的元素可以是任意类型
    val oddsAndEvents = List(List(1, 3, 5), List(2, 4, 6))
    println("oddsAndEvents = " + oddsAndEvents)
    //可以用index获取list的元素
    println("oddsAndEvents(1) = " + oddsAndEvents(1))
    //可以用head和tail函数实现list的遍历
    val primes = List(2, 3, 5, 7, 11, 13)
    var i = primes
    while (!i.isEmpty) {
      println(i.head)
      i = i.tail
    }
    //用isEmpty可以达到判断list是否为空
    //但因为所以的list的末尾都有一个Nil实例作为结束标志,所以只有一个元素的list调用tail,会返回一个Nil
    //因此,上述代码也可以这么写
    var j = primes
    //Nil实际是一个List[Nothing]的实例
    while (j != Nil) {
      println(j.head)
      j = j.tail
    }
    //实例化一个空的List,实际上就是返回Nil
    println("List()==Nil" + (List() == Nil))
    //或者用递归函数
    visitListItem(primes)

    //还有一种初始化list的房
    val numbers = 1 :: 2 :: 3 :: Nil
    println("numbers = " + numbers)

    //list的一些操作函数
    //连接两个list
    val combine: List[Int] = List(1, 2) ::: List(3, 4)
    println("combine = " + combine)
    //把另一个集合的元素加入list
    val add = List(1, 2) ++ Set(3, 4, 1)
    println("add = " + add)
    //去重
    println(List(1, 2, 3, 1, 6, 5, 3).distinct)
    //去掉开头的n个元素,返回剩余的
    //如果是一个空的list,调用drop(n),返回的还是一个空的list
    //但如果调用tail,则会报错
    //println(Nil.tail)
    println(Nil.drop(2))
    println(List(1, 23, 4, 141, 121) drop 2)
    //过滤,返回符合条件的元素
    println(List(1, 2, 3, 4, 5) filter (_ % 2 == 0))
    //把元素是list的list转化成一个list
    println(List(List(1, 3, 4), List(12, 14, 41)).flatten)
    //给list分组,传入一个predicate函数,返回一个含两个list的元组
    //前面的list包含所有调用predicate函数是true的元素,后面的list是false的
    println(List(1, 2, 3, 4, 5) partition (_ % 2 == 0))
    //翻转list
    println(List(1, 2, 3).reverse)
    //取一个list的切片,类似string的substring函数
    println(List(1, 2, 3, 4, 5, 6) slice (0, 3))
    //排序
    //根据两个元素调用predicate函数返回的true or false决定顺序
    println(List("apple", "orange", "banana") sortWith (_.size < _.size))
    //根据元素调用传入的函数的返回值排序
    println(List("apple", "orange", "banana") sortBy (_.size))
    //按默认属性排列
    println(List("apple", "orange", "banana") sorted)
    //切分list,返回一个有两个list的元组,按照在传入的index之前还是之后切分
    //如果传入的参数大于最大的index,则后一个list是空list
    //如果传入的参数小于等于0,则前一个是空list
    println(List(1, 2, 3, 4, 5) splitAt -1)
    //取开头的n元素,如果n大于实际list的长度,全部返回
    //如果传入的参数小于等于0,则前一个是空list
    println(List(1, 2) take 2)
    //拉链操作
    println(List(1, 2) zip List("a", "b"))

    //take drop ::这几个操作符因为是操作list的头,所以不会有性能问题
    //与之对应的,有一些操作list尾部的函数,如:+,dropRight, and takeRight
    val appended = List(1, 2, 3, 4, 5) :+ 6
    println(appended)
    println(appended takeRight 3)
    println(appended dropRight 3)

  }

  @tailrec
  def visitListItem[A](l: List[A]): Unit = {
    if (!l.isEmpty) {
      println(l.head)
      visitListItem(l.tail)
    }
  }
}
