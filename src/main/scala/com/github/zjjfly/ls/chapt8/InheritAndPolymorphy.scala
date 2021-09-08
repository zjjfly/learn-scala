package com.github.zjjfly.ls.chapt8

/**
  * Created by zjjfly on 16/4/8.
  */
object InheritAndPolymorphy {

  class A {
    def hi = "Hello from A"

    //使用override 关键字重载方法
    override def toString = getClass.getName
  }

  class B extends A

  class C extends B {
    //和java一样,子类中使用super表示父类的实例
    override def hi = "hi C->" + super.hi
  }

  def main(args: Array[String]) {
    val hiA = new A().hi
    println("hiA = " + hiA)
    val hiB = new B().hi
    println("hiB = " + hiB)
    val hiC = new C().hi
    println("hiC = " + hiC)

    val a1: A = new A
    //多态性
    val a2: A = new B
    //下面的这句错误
    //val b:B=new A

    //把A,B,C的实例放入list,产生的list类型是List[A]
    val misc = List(new A, new B, new C)
    misc.apply(1)
    misc(1)
    println(misc.isInstanceOf[List[A]])
    val msg: List[String] = misc.map(_.hi).distinct.sorted
    println("msg = " + msg)
  }
}
