package com.github.zjjfly.ls.chapt8

/**
  * Created by zjjfly on 16/4/7.
  */
object Users {

  class User1 {
    val name: String = "Yubaba"

    def greet: String = s"Hello from $name"

    override def toString = s"User($name)"
  }

  //改进,把name参数化
  class User2(n: String) {
    val name: String = n

    def greet: String = s"Hello from $name"

    override def toString = s"User($name)"
  }

  //再次改进,使初始化的参数可以在class中使用,而且这些参数会被当成成员变量,可以直接调用
  class User(val name: String) {

    def greet: String = s"Hello from $name"

    override def toString = s"User($name)"
  }

  def main(args: Array[String]) {
    val user1 = new User1
    println(user1.greet)
    val user2 = new User2("zjj")
    println(user2)
    println(user2.greet)
    val user = new User("jjzi")
    println(user)
    println(user.greet)
    val users =
      List(new User("Shotoaca"), new User("Art3mis"), new User("Aesch"))
    println(users map (_.name.length))
    println(users sortBy (_.name))
    val third: Option[User] = users find (_.name contains "7")
    println("third = " + third)
    val greet = third map (_.greet) getOrElse "Hi"
    println("greet = " + greet)
    val less6: List[User] = users takeWhile (_.name.length > 6)
    println("less6 = " + less6)

  }
}
