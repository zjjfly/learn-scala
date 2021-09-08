package com.github.zjjfly.ls.chapt10

/**
  * Created by zjjfly on 16/5/9.
  */
object TypeAndAliases {
  def main(args: Array[String]) {
    ///type alias
    type Whole = Int
    val x: Whole = 5

    type UserInfo = Tuple2[Int, String]
    val u: UserInfo = new UserInfo(123, "George")

    type T3[A, B, C] = Tuple3[A, B, C]
    val things = new T3(1, 'a', true)
  }
}

class User(val name: String)

trait Factory {
  //A就是abstract type
  type A;

  def create: A
}

trait UserFactory extends Factory {
  type A = User

  def create = new User("")
}
//还可以使用参数化类型实现上面的例子
trait Factory2[A] {
  def create: A
}
trait UserFactory2 extends Factory2[User] {
  def create = new User("")
}
