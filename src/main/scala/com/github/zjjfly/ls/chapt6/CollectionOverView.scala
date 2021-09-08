package com.github.zjjfly.ls.chapt6

/**
  * Created by zjjfly on 16/3/23.
  */
object CollectionOverView {
  def main(args: Array[String]) {
    //list
    val colors: List[String] = List("red", "green", "blue")
    println("colors.size = " + (colors.size))
    println("colors.head = " + (colors.head))
    println("colors.tail = " + colors.tail)
    println("colors(1) = " + colors(1))
    //可以用迭代Range(1 to 100)的方法迭代list
    val numbers = List(32, 95, 24, 21, 17)
    var total = 0
    for (i <- numbers) total += i
    println("total = " + total)
    for (c <- colors) println(c)

    //与java(8之前)的集合不同的是,scala的集合支持高级函数,如foreach,map,reduce
    //foreach是普遍使用的用于遍历集合的方法
    colors.foreach((c) => println(c))
    val sizes: List[Int] = colors.map((i) => i.size)
    println("sizes = " + sizes)
    val totals: Int = numbers.reduce((a, b) => a + b)
    println("totals = " + totals)

    //set,里面的元素不会重复
    val unique = Set(10, 20, 10, 20, 20, 30, 10)
    println("unique = " + unique)
    val sum: Int = unique.reduce((a, b) => a + b)
    println("sum = " + sum)

    //map
    val colorMap = Map("red" -> 0xff0000, "green" -> 0xff00, "blue" -> 0xff)
    val redRGB = colorMap("red")
    println("redRGB = " + redRGB)
    val cyanRGB = colorMap("green") | colorMap("blue")
    println("cyanRGB = " + cyanRGB)
    println("colorMap.contains(\" white\") = " + colorMap.contains("white"))
    for (pair <- colorMap) println(pair)
  }
}
