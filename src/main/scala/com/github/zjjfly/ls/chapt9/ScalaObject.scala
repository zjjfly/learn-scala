package com.github.zjjfly.ls.chapt9

/**
  * Created by zjjfly on 16/4/18.
  */
//scala的对象类似java的单例,还可以继承别的类
object ScalaObject extends com.github.zjjfly.ls.chapt8.Users.User("jjzi") {
  println("init object")

  def hi = "hi"

  def main(args: Array[String]): Unit = {
    //第一次使用object的时候才去初始化,之后就不再初始化了
    println(this)
    println(hi)
    println(
      HtmlUtils.removeMakeup("<html><body><h1>Introduction</h1></body></html>"))

    //伴生对象
    val tripler = Mutiplier(3)
    println(tripler.product(2))

    val conn = DBConnection
    println("args = " + args)
  }
}

//对象一般用于存放纯函数做工具函数和IO相关的函数,一般是和类的成员变量无关的函数
object HtmlUtils {
  def removeMakeup(input: String) = {
    input
      .replaceAll("""</?\w[^>]*>""", "")
      .replaceAll("<.*>", "")
  }
}

//scala的类一般会有一个相同名字的对象,叫伴生对象,一般定义在在同一个文件中
//伴生对象中定义的apply方法常是一个用工厂模式产生类的对象的方法
class Mutiplier(val x: Int) {
  def product(y: Int) = x * y
}

object Mutiplier {
  def apply(x: Int) = new Mutiplier(x)
}

//伴生对象和类在访问控制概念上被看做一个单元,所以他们可以互相访问对方的私有和受保护的成员和方法
object DBConnection {
  private val db_url = "jdbc:localhost"
  private val db_user = "zjj"
  private val db_pass = "19900104"

  def apply() = new DBConnection
}

class DBConnection {
  private val props = Map(
    "url" -> DBConnection.db_url,
    "user" -> DBConnection.db_user,
    "pass" -> DBConnection.db_pass
  )
  println(s"Create new connection for " + props("url"))
}
