package com.github.zjjfly.ls.chapt8

import java.util.Date

import scala.collection.immutable.Set

//全量导入
//import collection.mutable._
//组导入
import scala.collection.mutable.{ArrayBuffer, Queue, Set => MutSet}

//上面的就是包
/**
  * Created by zjjfly on 16/4/14.
  */
object Packaging {
  def main(args: Array[String]) {
    //使用其他包的类
    val d = new java.util.Date()
    //可以使用import导入类,和java一样
    //import的作用是把类导入当前的namespace
    val date = new Date()
    //scala的import和java不同的是,他可以放在任意可放表达式的的地方
    println("Your new UUID is " + {
      //这里的import只在这个语句块内部有效
      import java.util.UUID;
      UUID.randomUUID
    })
    //部分导入
    //new io.FileReader("1.txt")
    //全量导入
    val b = new ArrayBuffer[String]
    b += "Hello"
    println("b = " + b)
    val q = new Queue[Int]
    q.enqueue(1, 2, 3)
    //先进先出 FIFO
    println(q.dequeue())
    println(q.dequeue())
    //组导入,只导入一个包的部分类
    val m = Map(1 -> 2)
    println("m = " + m)
    //别名导入,这样不会和immutable set起冲突
    val set = MutSet(1, 2)
    println("set = " + set)
    val imSet = Set(1, 3)
    println("imSet = " + imSet)
  }
}

class Config(val baseUrl: String = "http://localhost")

object Config {
  def main(args: Array[String]) {
    println(new Config().baseUrl)
  }
}
