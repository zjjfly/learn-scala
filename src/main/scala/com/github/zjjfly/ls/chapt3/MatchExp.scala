package com.github.zjjfly.ls.chapt3

/**
  * 匹配表达式
  * Created by zjjfly on 16/3/11.
  */
object MatchExp {
  def main(args: Array[String]) {
    val x = 10
    val y = 20
    //用匹配表达式代替if else语句
    val max = x > y match {
      //=>后面可以跟多个表达式,但笔者推荐使用表达式块
      case true  => x
      case false => y
    }
    println("max = " + max)
    //另一个匹配表达式的例子
    val status = 500
    val message = status match {
      case 200 =>
        "ok"
      case 400 => {
        println("ERROR - we called the service incorrectly")
        "error"
      }
      case 500 => {
        println("ERROR - the service encountered an error")
        "error"
      }
    }
    println("message = " + message)
    //匹配的pattern可以用|连接,防止出现重复代码
    val day = "MON"
    val kind = day match {
      case "MON" | "TUE" | "WED" | "THU" | "FRI" =>
        "weekday"
      case "SAT" | "SUN" =>
        "weekend"
    }
    println("kind = " + kind)
    //上面的栗子如果输入的day="Monday",就好报一个MatchError的错误,这是因为找不到匹配的pattern,又没有百搭的pattern
    //有两种方式避免报错
    //1.value binding pattern
    //把找不到匹配的pattern的值绑定到一个变量,可以对这个变量做一些操作
    val msg = "error"
    val stat = msg match {
      case "ok" =>
        200
      case other => {
        println(s"Couldn't parse $other")
        -1
      }
    }
    println("stat = " + stat)
    //2.Wildcard Operator Pattern
    //用一个下划线表示匹配任意的值,但这样在case表达式中无法使用匹配的值,所以如果只需要输出一些消息的时候可以用这种
    val st = message match {
      case "Ok" => 200
      case _ => {
        println(s"Couldn't parse $message")
        -1
      }
    }
    println("st = " + st)

    //pattern guard
    val response: String = null
    response match {
      case s if s != null =>
        println(s"Received '$s'")
      case s =>
        println("Error! Received a null response")
    }
    //匹配类型,可以匹配出真正的类型,看栗子
    val n: Int = 1
    val m: Any = n
    println("m getClass = " + (m getClass))
    val re = m match {
      case x: String => s"'x'"
      case x: Double => f"$x%.2f"
      case x: Float  => f"$x%.2f"
      case x: Long   => s"${x}l"
      case x: Int    => s"${x}i"
    }
    //最终匹配上了Int类型
    println("re = " + re)

  }
}
