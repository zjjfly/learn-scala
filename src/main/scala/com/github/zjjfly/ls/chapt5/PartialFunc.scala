package com.github.zjjfly.ls.chapt5

/**
  * 部分函数
  * Created by zjjfly on 16/3/19.
  */
object PartialFunc {
  def main(args: Array[String]) {
    //部分函数指那些在所以的可能输入中只允许其中一部分的函数
    val statusHandler: Int => String = {
      case 200 => "Okay"
      case 400 => "Your Error"
      case 500 => "Our Error"
    }
    println("statusHandler(200) = " + statusHandler(200))
    println("statusHandler(100) = " + statusHandler(100))
  }
}
