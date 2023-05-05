package com.github.zjjfly.ls.chapt6

import scala.language.postfixOps

/**
  * list中类似reduce的高阶函数
  * Created by zjjfly on 16/3/28.
  */
object ReduceList {
  def main(args: Array[String]) {
    val list = List(1, 2, 3, 4, 5)
    //Math reduction operations
    //求最大值
    println("list max = " + (list max))
    //最小值
    println("list min = " + (list min))
    //所以元素的积
    println("list product = " + (list product))
    //所以元素的和
    println("list sum = " + (list sum))

    //Boolean reduction operations
    //判断list是否含某个元素
    println("(list contains 12) = " + (list contains 12))
    //是否已给定的list里的元素结尾
    println("(list endsWith List(1,2)) = " + (list endsWith List(4, 5)))
    //判断list中是否有符合条件的元素,传入predicate函数
    println("(list) exists (_<3)= " + (list exists (_ < 3)))
    //判断list的每个元素是否符合条件,全符合才返回true
    println("(list forall (_>0)) = " + (list forall (_ < 3)))
    //判断list的开头是否是给定的list里的元素
    println("(list startsWith List(1,2)) = " + (list startsWith List(1, 2)))

    //自己实现contains
    println("contains(2,list) = " + contains(2, list))
    //自己实现reduce
    //实现contains
    val include = boolReduce(list, false) { (a, i) =>
      if (a) a else (i == 10)
    }
    println("include = " + include)
    //实现forall
    println("all of list is bigger than 0 = " + boolReduce(list, true) {
      (a, i) =>
        if (!a) a else a == (i > 0)
    })
    //更通用的实现
    //实现max
    val max = reduceOp(list, 0) { (a, l) =>
      if (a > l) a else l
    }
    println("max = " + max)
    //实现sum
    val sum = reduceOp(list, 0)(_ + _)
    println("sum = " + sum)

    //使用scala内置的fold函数
    val l = List(3, 5, 6, 7, 9)
    //一般的fold函数,第一个参数是起始值
    println("l.fold(0)(_+_) = " + l.fold(0)(_ + _))
    //从左到右fold,
    println("l.foldLeft(0)(_+_) = " + l.foldLeft(0)(_ + _))
    //从右到左
    println("l.foldRight(0)(_+_) = " + l.foldRight(0)(_ + _))
    //reduce系列函数,起始值是第一个元素
    println("l reduce (_+_) = " + (l reduce (_ + _)))
    println("l reduceLeft (_+_) = " + (l reduceLeft (_ + _)))
    println("l reduceRight (_+_) = " + (l reduceRight (_ + _)))
    //scan,和reduce类似,但它会把每次累加的结果放入一个list返回
    println("l.scan(0)(_+_) = " + l.scan(0)(_ + _))
    println("l.scan(0)(_+_) = " + l.scanLeft(0)(_ + _))
    //scanRight的返回的累加结果也是按先后从右到左的
    println("l.scan(0)(_+_) = " + l.scanRight(0)(_ + _))
    //reduce,fold,scan和他们伴生的left/right函数的区别是
    //前者只能返回和list的类型一样的值,但后者支持不一样类型的值
    //还有一个区别是,前者的执行顺序是未知的,但后者有方向性
    //所以,一般我们用带left的函数

    //用自己的fold实现reduce
    println("reduce(l)(_+_) = " + reduce(l)(_ + _))
    //用

    //用scala的foldLeft实现contains
    val inc = List(1, 14, 141).foldLeft(false) { (a, i) =>
      if (a) a else i == 1
    }
    println("inc = " + inc)
    //用scala的reduceleft实现sum
    val answer = List(11.3, 13.4, 51.5) reduceLeft (_ + _)
    println("answer = " + answer)
  }

  //自己实现contains函数
  def contains(x: Int, l: List[Int]): Boolean = {
    var a = false
    for (i <- l) {
      a = (i == x)
    }
    a
  }

  //自己实现bool reduce
  def boolReduce(l: List[Int], start: Boolean)(
      f: (Boolean, Int) => Boolean): Boolean = {
    var a = start
    for (i <- l) a = f(a, i)
    a
  }
  //自己实现更通用的reduce
  def reduceOp[A, B](l: List[A], start: B)(f: (B, A) => B): B = {
    var a = start
    for (i <- l) a = f(a, i)
    a
  }

  //用fold实现reduce
  def reduce[A](l: List[A])(f: (A, A) => A) = {
    if (l == Nil) Nil else l.fold(0.asInstanceOf[A])(f)
  }
}
