package com.github.zjjfly.ls.chapt10

/**
  * Created by zjjfly on 16/5/6.
  */
object TupleAndFuncValue {
  def main(args: Array[String]) {
    //TupleN是一些case class ,这些class扩展的是ProductN特质
    //它有一个productArity方法可以返回元组的元素个数,
    //还有一个productElement可以安全的访问元组的元素,传入的参数是index
    val x: (Int, Int) = Tuple2(10, 20)
    println("Does the arity = 2? " + (x.productArity == 2))

    //函数字面量实际是一个继承了FunctionN特质并实现了特质的apply方法的匿名类,调用这个函数字面量就是调用apply方法
    val hello = new Function1[String, String] {
      def apply(n: String) = s"Hello, $n"
    }
    val world = new Function1[String, String] {
      def apply(n: String) = "world"
    }
    println(hello("jjzi"))
    //FunctionN重写了toString方法,返回值是<functionN>
    println(hello)
    //Function1有一个两个特殊的其他FunctionN都没有的方法,andThen和compose
    //它们可以把函数组合起来,变为一个新的函数.按顺序执行,前面执行的函数的返回值作为下一个函数的输入
    //andThen是从左到右执行,compose相反
    println((world andThen (hello))(""))
    println((world compose (hello))(""))

  }
}
