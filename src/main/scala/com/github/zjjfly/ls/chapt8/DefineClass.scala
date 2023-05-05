package com.github.zjjfly.ls.chapt8

/**
  * Created by zjjfly on 16/4/8.
  */
object DefineClass {

  //最普通的类定义
  //默认参数
  class Car(val make: String,
            var reserved: Boolean = true,
            val year: Int = 2015) {
    def reserve(r: Boolean): Unit = {
      reserved = r
    }
  }

  //继承
  class Lotus(val color: String, reserved: Boolean)
      extends Car("Lotus", reserved) {}

  //类型参数化
  class Singular[A](element: A) extends Iterable[A] {
    override def foreach[B](f: A => B) = f(element)

    override def iterator: Iterator[A] = ???
  }

  def main(args: Array[String]): Unit = {
    val t = new Car("Toyota")
    t.reserve(true)
    println(s"My ${t.make} is now reserved? ${t.reserved}")
    //和函数一样,可以用named parameter
    val t2 = new Car(reserved = false, make = "Tesla")
    println(t2.make)
    val l: Lotus = new Lotus("red", false)
    println(s"Requested a ${l.color} ${l.make}")

    val p = new Singular("Planes")
    p foreach println
    val name = p.head
    println("name = " + name)
  }
}
