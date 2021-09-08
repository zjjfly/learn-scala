package com.github.zjjfly.ls.chapt10

/**
  * Created by zjjfly on 16/5/11.
  */
object TypeVariance {
  def main(args: Array[String]) {
    //一般的多态
    val c1: Car = new Volvo()
    println(c1)
    val c2: Item[Car] = Item[Volvo](new Volvo)
    //c2.get的类型是Car
    println(c2)
    //  item( new Item[Car](new Car())) 错误
    item(new Item[Volvo](new Volvo))
    item(new Item[VolvoWagon](new VolvoWagon))
    check(new Check[Car])
    check(new Check[Volvo])
//  check(new Check[VolvoWagon]()) 错误

    //package object
    val m = new Mappy[Int, String]
  }

  def item(v: Item[Volvo]) {
    val c: Car = v.get
  }

  def check(v: Check[Volvo]) {
    v.check(new VolvoWagon())
  }

}

class Car {
  override def toString =
    "Car()"
}

class Volvo extends Car {
  override def toString = "Volvo()"
}

class VolvoWagon extends Volvo

//协变,如果B是A的子类,那么Item[B]可以认为是Item[A]的子类
case class Item[+A](a: A) {
  def get: A = a
}

//协变的类型不能用于指定函数参数的类型,因为父类无法强制转换成子类
//class Check[+A] { def check(a: A) = {} }
//函数的参数的类型要  用逆变
//如果B是A的子类,Check[A]可以认为是Check[B]的子类
class Check[-A] {
  def check(a: A) = {}
}
