package com.github.zjjfly.ls.chapt8

import scala.annotation.tailrec

/**
  * Created by zjjfly on 16/4/16.
  */
class Exercise2 {}

//a
class CustomList[A](items: A*) {
  val item: Option[A] = items.headOption
  val next: Option[CustomList[A]] = {
    //:_*告诉编译器,把某个参数当做参数序列传递给方法
    if (item isDefined) Some(new CustomList[A](items.tail: _*)) else None
  }

  def foreach(f: A => Unit): Unit = {
    for (i <- item; n <- next) {
      f(i)
      n.foreach(f)
    }
  }

  def apply(index: Int): Option[A] = {
    index < 0 match {
      case true  => None
      case false => if (index < 1) item else next flatMap (_.apply(index - 1))
    }
  }
}

//b和c
class ListyHelper {
  def create[A](items: A*) = {
    var result: Listy[A] = new EmptyList[A]
    for (item <- items.reverse) {
      result = new NonEmptyList[A](item, result)
    }
    result
  }
}

abstract class Listy[A] {
  def foreach(f: A => Unit): Unit

  def apply(index: Int): Option[A]

  def headOption: Option[A] = apply(0)

  lazy val head = headOption.get

  def tail: Listy[A]

  def filter(f: A => Boolean): Listy[A] = {
    var result: Listy[A] = new EmptyList[A]
    foreach { i =>
      if (f(i)) {
        result = new NonEmptyList[A](i, result)
      }
    }
    result.reverse
  }

  lazy val size: Int = {
    var count = 0
    foreach { _ =>
      count += 1
    }
    count
  }

  def map[B](f: A => B): Listy[B] = {
    var result: Listy[B] = new EmptyList[B]
    foreach { i =>
      result = new NonEmptyList[B](f(i), result)
    }
    result.reverse
  }

  lazy val reverse: Listy[A] = {
    var result: Listy[A] = new EmptyList[A]
    foreach { i =>
      result = new NonEmptyList[A](i, result)
    }
    result
  }
}

class NonEmptyList[A](val item: A, val tail: Listy[A]) extends Listy[A] {

  override def foreach(f: A => Unit): Unit = {
    f(item)
    tail.foreach(f)
  }

  override def apply(index: Int): Option[A] = {
    if (index < 1) Some(item) else tail.apply(index - 1)
  }
}

class EmptyList[A] extends Listy[A] {
  override def foreach(f: A => Unit): Unit = {}

  override def apply(index: Int): Option[A] = None

  override def tail = null
}

object Exercise2 {
  def main(args: Array[String]) {
    val cl = new CustomList[Int](1, 3)
    val cl1: Option[Int] = cl(1)
    println("cl1 = " + cl1)
    val ints: List[Int] = List()
    println(ints.head)
  }
}
