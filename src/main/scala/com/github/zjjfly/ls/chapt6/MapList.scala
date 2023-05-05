package com.github.zjjfly.ls.chapt6

import scala.language.postfixOps

/**
  * List中类似map的函数
  * Created by zjjfly on 16/3/28.
  */
object MapList {
  def main(args: Array[String]) {
    val list = List(1, 2, 3, 4, 5)
    //转换list每个元素,传入的是一个partial函数
    //结果是留下partial函数可以接受的元素
    println(list collect {
      case 1 => "ok"
      case 2 => "no"
    })
    //使用传入的函数转换每个元素,并把得到的集合(字符串)扁平化地放入list
    println(List("milk,tea", "jj,zi") flatMap (_ toUpperCase))
    //使用传入的函数转换每个元素,并放入list
    println(list map (_ + 1))

  }
}
