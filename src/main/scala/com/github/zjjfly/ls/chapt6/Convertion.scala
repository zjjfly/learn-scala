package com.github.zjjfly.ls.chapt6

import java.util

import collection.JavaConverters._
import scala.collection.mutable

/**
  * Created by zjjfly on 16/3/28.
  */
object Convertion {
  def main(args: Array[String]) {
    val l: List[Int] = List(24, 99, 105)
    //把list的各个元素用分隔符分隔,返回字符串
    println("l mkString \", \" = " + (l mkString ","))
    //转化list,由不可变变成可变的
    println("l toBuffer = " + (l.toBuffer))
    //把map转化成list
    println(Map("a" -> 1, "b" -> 2).toList)
    //把二元组的集合转化成map
    println(List(1 -> true, 3 -> false).toMap)
    println(Set(1 -> true, 3 -> false).toMap)
    //把集合变成set
    println(List(2, 4, 5, 5, 7).toSet)
    //把集合转化成string
    println(List(2, 4, 5, 5, 7).toString)

    //scala集合和java的集合互相转换
    println("l.asJava = " + l.asJava.isInstanceOf[java.util.List[Int]])
    println("l.asJava = " + l.asJava.isInstanceOf[List[Int]])
    val list1: util.ArrayList[Integer] = new util.ArrayList(4)
    list1.add(1)
    list1.add(2)
    val list = list1.asScala
    println("list = " + list)

  }
}
