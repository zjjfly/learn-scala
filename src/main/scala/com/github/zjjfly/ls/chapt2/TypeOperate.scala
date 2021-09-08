package com.github.zjjfly.ls.chapt2

/**
  * 类型的共有的一些操作
  * Created by zjjfly on 16/3/10.
  */
object TypeOperate {
  def main(args: Array[String]) {
    val i = 1;
    //类型强制转换,类似java的(类型),最好不要使用,而是用那些to开头函数
    val l: Long = i.asInstanceOf[Long]
    println("Is l a long value?" + l.isInstanceOf[Long])
    //类型转换方法,一般形式是to<type>
    val long: Long = i.toLong
    //得到对象的类型
    println(i.getClass)
    //判断是否是某个类型
    println(l.isInstanceOf[Long])
    //hashCode,toString,这两个方法凡是基于JVM的对象都要有
    println("i hashCode = " + (i hashCode))
    println("i toString = " + (i toString))

  }
}
