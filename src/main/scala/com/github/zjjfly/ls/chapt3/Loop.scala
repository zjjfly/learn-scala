package com.github.zjjfly.ls.chapt3

import scala.collection.immutable.IndexedSeq

/**
  * Created by zjjfly on 16/3/11.
  */
object Loop {
  def main(args: Array[String]) {
    //Range,一种循环中常用的数据结构,有两种产生方法
    //1.to,包含起始和终止的数字
    println("1 to 3 = " + (1 to 3))
    //2.util,不包含结束的数字
    println("(1 until 3) = " + (1 until 3))
    //基本的循环语句
    //    for (<identifier> <- <iterator>) [yield] [<expression>]
    //其中yield可选的,它会把每次expression产生的返回值放入一个集合中返回
    //循环条件可以用()或者{},用()的话,里面的语句除了最后一句都要加分号结束,而{}不用,下面的例子对此进行了说明
    for (i <- 1 to 3;
      j<-1 to 3) {
      print(i+" "+j)
    }
    println()
    for {i <- 1 to 3
      j<-1 to 3} {
      print(i+" "+j)
    }
    println()
    //如果表达式只有一行代码,可以省略{}
    for (i<-1 to 4) println(s"day $i :")
    //如果需要用上面代码zhong的day X :这样的数据,需要加yield,并复制给一个变量
    val days=for (i<-1 to 4) yield {s"day $i :"}
    for (day<-days) print(day+",")
    //iterator guard
    //类似之前的match guard,可以在循环条件语句中加入if
    val threes=for (i<-1 to 20 if i%3==0) yield i
    println
    println("threes = " + threes)
    //iterator guard可以和iterator分开,看下面的例子
    val quote="Faith,Hope,,Charity"
    for{
      t<-quote.split(",")
      if !(t isEmpty)
    }{
      println(t)
    }
    //嵌套的for循环
    for{
      x<-1 to 2
      y<-1 to 0
    }{
      print(s"($x,$y)")
    }
    //数据绑定
    //for语句的循环定义中,可以加入数据绑定,这样可以最小化循环表达式的复杂度
    //下面是例子
    val powersOf2: IndexedSeq[Int] = for(i<-0 to 8; pow=1<<i) yield pow
    println(powersOf2)

    //while和do while,它们不是表达式,所以不能用yield
    var x=10
    while(x>0) x -= 1
    println(x)
    val n=0
    do println(s"Here I am,x=$n") while (x>0)
    //虽然while和do-while有它们的使用场景,但scala中有更具表现力和实用的方法
  }
}
