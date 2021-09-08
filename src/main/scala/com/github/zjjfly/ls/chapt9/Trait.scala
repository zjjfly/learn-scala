package com.github.zjjfly.ls.chapt9

import java.util._

/**
  * Created by zjjfly on 16/4/25.
  */
object Trait {
  def main(args: Array[String]) {
    println(
      new Page("<html><body><h1>Introduction</h1></body></html>").asPlainText)
    println(new Page("").asPlainText)
    println(new Page(null).asPlainText)

    println(new D())

    val green = new RGBColor(255 << 8).hex
    println("green = " + green)

    val red = new Paint(128 << 16).hex

    val blue = new Overlay(192).hex

    println("red = " + red)

    println("blue = " + blue)

    //利用特质扩展原生类,但一般不会使用这种方法扩展,而是用隐式转换
    val list = new ArrayList[String]() with customList[String]
    println("list = " + list)
    println("list = " + list.say())

    //带特质的实例化
    val h = new Users("Harry P") with Wizard
    val g = new Users("Ginny W") with Attorney
    val l = new Users("Luna L") with Wizard with Reverser
    println("h = " + h)
    println("g = " + g)
    println("l = " + l)
    val title = ""
    //导入实例成员
    val latteReceipt = Reciept(123, 4.12, "fred", "Medium Latte")
    //如果同一个命名空间有相同名称的变量,那优先用已经存在的变量,所以import语句要尽量靠近它们被使用的语句
    import latteReceipt.{amout, who}
    println(s"Sold a $title for $amout to $who")

    import util.Random._
    //alphanumeric.take(n),返回n个随机英文字母
    val letters = alphanumeric.take(20).toList.mkString
    println("letters = " + letters)
    //shuffle()
    val numbers = shuffle(1 to 20)
    println("numbers = " + numbers)

  }
}

//特质的语法,它不能有构造参数
trait HtmlUtil {
  def removeMarkup(input: String) = {
    input
      .replaceAll("""</?\w[^>]*>""", "")
      .replaceAll("<.*>", "")
  }
}

//继承特质
//class Page(val s:String) extends HtmlUtils{
//  def asPlainText=removeMarkup(s)
//}

trait SafeStringUtils {
  def trimToNone(s: String): Option[String] = {
    Option(s) map (_.trim) filterNot (_.isEmpty)
  }
}

//继承多个特质
class Page(val s: String) extends SafeStringUtils with HtmlUtil {
  def asPlainText: String = {
    trimToNone(s).map(removeMarkup).getOrElse("n/a")
  }
}

//继承多个特质实际是线性的继承,最右边的特质是最底层的基类
trait Base {
  override def toString = "Base"
}

class A extends Base {
  override def toString = "A->" + super.toString
}

trait B extends Base {
  override def toString = "B->" + super.toString
}

trait C extends Base {
  override def toString = "C->" + super.toString
}

class D extends A with B with C {
  override def toString =
    "D->" +
      super.toString
}

//线性化的好处是可以写特质去重载(特质和子类)共享的父类的行为
class RGBColor(val color: Int) {
  def hex = f"$color%06X"
}

trait Opaque extends RGBColor {
  override def hex = s"${color}FF"
}

trait Sheer extends RGBColor {
  override def hex = s"${color}33"
}

class Paint(color: Int) extends RGBColor(color) with Opaque

class Overlay(color: Int) extends RGBColor(color) with Sheer

class NM(color: Int) {
  def hex = color + ""
}

trait customList[A] extends AbstractList[A] {
  override def toString = "d"

  def say() = println("Hi")
}

//self type 用来限定某个特质只能混入某个类或它的子类
class AA {
  def hi = "hi"
}

trait BB {
  self: AA =>
  override def toString = "B:" + hi
}

//下面的会报错
//class CC extends BB
//下面的就对了
class CC extends AA with BB

//self type的好处是可以不指明类参数而扩展类,下面是例子
class TestSuite(suiteName: String) {
  def start() {}
}

trait RandomSeeded {
  self: TestSuite =>
  def randomStart() {
    util.Random.setSeed(System.currentTimeMillis)
    self.start()
  }
}

class IdSpec extends TestSuite("ID Tests") with RandomSeeded {
  def testId() {
    println(util.Random.nextInt != 1)
  }

  override def start() {
    testId()
  }

  println("Starting...")
  randomStart()
}

//带特质的实例化
class Users(val name: String) {
  def suffix = ""

  override def toString = s"$name$suffix"
}

trait Attorney {
  self: Users =>
  override def suffix = ", esq."
}

trait Wizard {
  self: Users =>
  override def suffix = ", Wizard"
}

trait Reverser {
  override def toString = super.toString.reverse
}

//导入对象成员
case class Reciept(id: Int, amout: Double, who: String, title: String)
