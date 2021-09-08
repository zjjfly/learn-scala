package com.github.zjjfly.ls.chapt8

/**
  * Created by zjjfly on 16/4/14.
  */
object MoreFieldAndMethodType {

  //函数重载,scala重载不能只改变方法的返回值类型,否则会报编译错误
  //一般更推荐使用默认值参数的方法而不是去重载方法
  class Printer(msg: String) {
    def print(s: String): Unit = println(s"$msg:$s")

    def print(l: Seq[String]): Unit = print(l mkString ",")
  }

  //apply方法,这种方法可以直接用对象名+(参数)调用
  //一般把某种会非常频繁用到的操作定义为apply方法,而且语义上看起来不能太奇怪
  class Multiplier(factor: Int) {
    def apply(input: Int) = input * factor
  }

  class RandomPoint {
    val x = {
      println("creating x"); util.Random.nextInt
    }
    lazy val y = {
      println("now y"); util.Random.nextInt
    }
  }

  def main(args: Array[String]) {
    //调用重载方法
    new Printer("Today's Report").print("Foggy" :: "Rainy" :: "Hot" :: Nil)
    //调用apply方法
    val tripleMe = new Multiplier(3)
    println(tripleMe.apply(10))
    println(tripleMe(10))
    //lazy value,只在调用这个值的时候才会对它初始化
    //用于修饰那些只在真正需要的时候才去初始化的值,一般用val,保证不会被覆盖
    val p = new RandomPoint
    //x在p初始化的时候就赋值了,而y在输出的时候才赋值
    println(s"Location is ${p.x}, ${p.y}")
  }
}
