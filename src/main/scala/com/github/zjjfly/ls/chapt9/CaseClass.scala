package com.github.zjjfly.ls.chapt9

/**
  * Created by zjjfly on 16/4/22.
  */
object CaseClass {
  def main(args: Array[String]) {
    val user: User = User("jjzi", "123456")
    //自动生成的copy方法
    val u: User = user.copy()
    //自动生成的toString方法
    println("u = " + u)
    //自动生成的equals方法
    println(user == u)
    //生成的模式匹配用的unapply方法
    u match {
      case User("jjzi", "123456") => println("hah")
      case _                      => println("hehe")
    }
    val c = Chars("dd")
    println("c = " + c)
  }
}

//case class会自动生成类的伴生对象,并且还会在类和对象中生成一些方法
//如toString和equals方法
//case class可以看做和java的java bean作用类似
case class User(name: String, passwd: String)
//case class自动生成方法清单:
//class中有:copy,equals,hashCode,toString
//object中有:apply,unapply

//如果一个case class使用它的构造参数继承了一个class,则被继承的类的字段不会加入case class,自动生成的方法也不会使用这些字段
case class Chars(user: String) extends com.github.zjjfly.ls.chapt8.MoreClassType.Mini(13, user)
