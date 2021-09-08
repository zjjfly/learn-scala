package com.github.zjjfly.ls.chapt8

import scala.util.Random

/**
  * Created by zjjfly on 16/4/15.
  */
object PrivacyControl {
  def main(args: Array[String]) {
    val isValid = new ValidUser().isValid
    println("isValid = " + isValid)
    val user = new User()
    //user.passwd 报错

    val u = new MUser("13414")
    val validate: Boolean = u.validate("13141")
    println("validate = " + validate)
    u.update("13141")
    println(u.validate("13141"))
  }

  //protected,和java的protect类似,只能在类内部和子类中可见
  class User {
    protected val passwd = Random.nextString(10)
  }

  class ValidUser extends User {
    def isValid = !passwd.isEmpty
  }
  //private,它其实等价于private[this]
  class MUser(private var password: String) {
    def update(p: String): Unit = {
      println("Modifying password!")
      password = p
    }
    def validate(p: String) = p == password
  }

  //final
  final class A {}
  //由于final,下面的会报错
  //class B extends A{
  //
  //}
  class B {
    final def b = ""
  }
  class C extends B {
    //由于final,下面的会报错
    //  def b="f"
  }

  //sealed ,表示这个类只能被同一个文件的类继承
  //scala典型的使用sealed的例子是Option
  sealed abstract class D {
    def a: Int => Int
  }
}
//access modifier
package com.oreilly {

  private[oreilly] class Config {
    val url = "http://localhost"
  }

  class Authentication {
    private[this] val password = "jason"

    // TODO change me
    def validate = password.size > 0
  }

  class Test {
    println(s"url = ${new Config().url}")
    println("" + new TestM().s)

    private[Test] class TestM {
      val s = "dada"
    }

  }

  object Test {
    def main(args: Array[String]) {
      val authentication: Authentication = new Authentication()
      println(authentication.validate)
      new Test()
    }
  }
}
