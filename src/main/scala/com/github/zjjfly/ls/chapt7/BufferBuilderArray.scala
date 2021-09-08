package com.github.zjjfly.ls.chapt7

import java.io.File

import scala.collection.mutable

/**
  * Created by zjjfly on 16/3/31.
  */
object BufferBuilderArray {
  def main(args: Array[String]) {
    //不可变类型,对它的操作返回的是一个新的map,原map不会改变
    val m = Map("AAPL" -> 597, "MSFT" -> 40)
    //map的删除元素和添加元素操作
    val n = m - "AAPL" + ("GOOG" -> 531)
    println(m)

    //可变类型
    //buffer
    val nums = mutable.Buffer(1)
    for (i <- 2 to 10) nums += i
    println("nums = " + nums)

    val nums1 = mutable.Buffer[Int]()
    for (i <- 1 to 10) nums1 += i
    println("nums1 = " + nums1)
    //转成不可变的list
    println("nums.toList = " + nums.toList)

    //map
    val map = mutable.Map((1 -> 2), (2 -> 3))
    println("m = " + map)
    map += (3 -> 4)
    println("map = " + map)
    //转成不可变的map
    println("map.toMap = " + map.toMap)
    //set
    val set = mutable.Set(1, 3)
    for (i <- 4 to 10) set += i
    println("set = " + set)
    //转成不可变的set
    println("set.toSet = " + set.toSet)

    //不可变变成可变,用toBuffer,map,set也用这个函数
    val mb: mutable.Buffer[(String, Int)] = m.toBuffer
    println("mb = " + mb)
    //toBuffer函数得到的是一个ArrayBuffer对象
    //trimStart左右和drop类似
    mb trimStart 1
    mb += ("GOOG" -> 531)
    println("mb = " + mb)
    println("mb.toMap = " + mb.toMap)
    //buffer支持转换成list,map和set
    //从map转换来的buffer不一定要转回map,可以转成list或set
    //转成set
    mb += ("GOOG" -> 531)
    println("mb = " + mb)
    println("mb.toSet = " + mb.toSet)
    //转成list
    println("mb.toList = " + mb.toList)
    //buffer的缺点是适用性太广

    //Builder,它只支持添加元素
    //为特定的集合类型生成一个builder,要调用这个集合类的newBuilder方法,并指明元素的类型
    val builder: mutable.Builder[Char, Set[Char]] = Set.newBuilder[Char]
    //添加元素
    builder += 'h'
    //添加其他集合内的元素
    builder ++= List('e', 'r', 'o')
    //转换回不可变集合
    val heroSet: Set[Char] = builder.result
    println("heroSet = " + heroSet)
    //builfer相对于Buffer,在下面这种时候是比较好的选择
    //你需要迭代的构建一个可变集合并且要把它转换成不可变集合

    //还有一种长度固定,里面的元素可变的集合Array
    //他不是一个正式的集合,因为它不在scala.collections包里,也没有继承自Iterable类
    //但它有Iterable所有的操作,如filter,map
    //它实际上是对java的数组的封装,使用的是implicit class
    //scala提供这个类是为了与java和jvm的兼容性
    val colors = Array("red", "green", "blue")
    colors(0) = "purple"
    colors map (println)
    println("very purple" + colors)
    val files = new File(".").listFiles
    files map (println)
    val scala = files map (_.getName) filter (_.endsWith(".scala"))
    println(scala.toList)
    //一般不推荐用Array,除非你需要它和其他jvm语言互操作

  }
}
