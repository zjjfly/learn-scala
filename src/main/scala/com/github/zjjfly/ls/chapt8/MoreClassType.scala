package com.github.zjjfly.ls.chapt8

import java.util.Date

/**
  * Created by zjjfly on 16/4/12.
  */
object MoreClassType {

  //抽象类
  abstract class Car {
    val year: Int
    val automatic: Boolean = true

    def color: String
  }

  //实现抽象类,使用input parameter
  //可以使用一个值实现一个方法
  class Mini(val year: Int, val color: String) extends Car

  abstract class Listener {
    def trigger
  }

  class Listening {
    var listener: Listener = null

    def register(l: Listener) {
      listener = l
    }

    def sendNotification() {
      listener.trigger
    }
  }

  def main(args: Array[String]): Unit = {
    //匿名类,如果某个类的一个子类只用到一次,可以使用匿名类
    val myListener = new Listener {
      override def trigger: Unit = {
        println(s"Trigger at ${new Date()}")
      }
    }
    myListener.trigger
    //另一个例子,更接近java gui中的Listener
    val notification = new Listening
    notification.register(new Listener {
      def trigger: Unit = {
        println(s"Trigger at ${new java.util.Date}")
      }
    })
    notification.sendNotification()
  }
}
