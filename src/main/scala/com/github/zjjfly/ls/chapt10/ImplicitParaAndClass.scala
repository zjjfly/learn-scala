package com.github.zjjfly.ls.chapt10

/**
  * Created by zjjfly on 16/5/9.
  */
object ImplicitParaAndClass {
  def main(args: Array[String]) {
    USD(1.2).print
    import IntUtils._
    println(3.fishes)
  }
}

object Doubly {
  //implicit parameter
  def print(num: Double)(implicit fmt: String) = {
    println(fmt format num)
  }
}

case class USD(amount: Double) {
  implicit val printFmt = "%.2f"

  def print = Doubly.print(amount)
}

object IntUtils {
  //implicit class,在2.10之后取代了隐式转换函数
  //implicit class有三个限制
  //1.它必须定义在object,class或trait中
  //2.必须只有单个非implicit类型的参数
  //3.它的名字不能和当前的命名空间的object,class,trait相同
  implicit class Fishies(val x: Int) {
    def fishes = "Fish" * x
  }
}
