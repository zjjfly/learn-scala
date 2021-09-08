package com.github.zjjfly.ls.chapt10

import java.util

/**
  * Created by zjjfly on 16/5/10.
  */
object BoundedType {
  //upper bounded type,A只能是BaseUser或它的子类
  def check[A <: BaseUser](u: A) {
    if (u.name.isEmpty) println("Fail!") else println("Success")
  }

  //view-bound,类似upper bounded,但支持隐式转换
  def checks[A <% BaseUser](u: A): Unit = {
    println(u.name.isEmpty)
  }

  implicit class MyCustomer(s: String) extends BaseUser(s)

  def recruit[A >: Customer](u: Customer): A = u match {
    case p: PreferredCustomer => new PreferredCustomer(u.name)
    case c: Customer          => new Customer(u.name)
  }

  def main(args: Array[String]) {
    check(new Customer("Fred"))
    check(new Admin("", "strict"))
    check(new PreferredCustomer("sada"))
    checks("dad")
    val customer = recruit(new Customer("Fred"))
    println(customer)
    //实际上recruit在返回结果之前会做一次强制类型转换,统一转成A类型,如果没有指明A类型,则转成Object,返回之后又会转成Customer
    val preferred = recruit(new PreferredCustomer("George"))
    val v1 = new SecurityCard().verify(new PreferredCustomer("George"))
  }
}

class BaseUser(val name: String)

class Admin(name: String, val level: String) extends BaseUser(name)

class Customer(name: String) extends BaseUser(name)

class PreferredCustomer(name: String) extends Customer(name)

abstract class Card {
  //abstract type和bounded type结合
  type UserType <: Customer

  def verify(u: UserType): Boolean
}

class SecurityCard extends Card {
  type UserType = PreferredCustomer

  def verify(u: PreferredCustomer) = true
}
