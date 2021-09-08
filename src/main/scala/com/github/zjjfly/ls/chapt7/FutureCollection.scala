package com.github.zjjfly.ls.chapt7

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.{Duration, _}
import scala.concurrent.{Await, Future}
import scala.io.Source
import scala.util.{Failure, Success, Try}

/**
  * 类似java的Future
  * Created by zjjfly on 16/4/5.
  */
object FutureCollection {
  def main(args: Array[String]) {
    //产生一个future
    //    val f = Future {
    //      println("hi")
    //    }
    //    println("f = " + f)
    //    val future =Future {
    //      Thread.sleep(2000);
    //      1
    //    }
    //对future进行异步处理
    def nextFtr(i: Int = 0) = Future {
      def rand(x: Int) = util.Random.nextInt(x)
      //实践中不推荐用sleep,应该用回调函数
      Thread.sleep(rand(2000))
      if (rand(3) > 0) (i + 1) else throw new Exception("13131")
    }
    //链接两个future,返回一个更高层的future,如果第一个future失败,调用第二个
    //val fallback: Future[Int] = nextFtr(1) fallbackTo nextFtr(2)

    //链接两个future,返回一个更高层的future,如果第一个futur成功,使用第一个的返回值调用第二个
    //val flat: Future[Int] = nextFtr(1) flatMap nextFtr

    //链接一个future和一个函数,返回一个更高层的future,如果futur成功,对返回值调用函数
    //val map: Future[Int] = nextFtr() map (_*2)

    //成功后执行函数,传入的参数是future的返回值
    //    nextFtr() onSuccess { case x => println(x)}
    //    Thread sleep 2000
    //失败后执行函数,传入的是future抛出的exception
    //    nextFtr() onFailure {case e=>e.printStackTrace}
    //    Thread sleep 2000
    //完成后执行函数,传入的是一个Try
    //    nextFtr() onComplete {
    //      case Success(x)=>println(x)
    //      case Failure(ex)=>ex.printStackTrace
    //    }
    //    Thread sleep 2000
    //把若干个Future放入一个序列中,生产一个新的Future,当所有的future成功时,会返回一个由返回值组成的list
    //如果有一个失败了,则会马上返回
    //val eventualInts: Future[List[Int]] = Future sequence List(nextFtr(1),nextFtr(5),nextFtr(3))
    //eventualInts onFailure {case x:Throwable=>x.printStackTrace}
    //Thread sleep 6000
    def cityTemp(name: String): Double = {
      val url = "http://api.openweathermap.org/data/2.5/weather"
      val cityUrl = s"$url?q=$name&appid=e4f62ff92c4b71d4919db54a210bb320"
      val json = Source.fromURL(cityUrl).mkString.trim
      val pattern = """.*"temp":([\d.]+).*""".r
      val pattern(temp) = json
      temp.toDouble
    }
    val cityTemps = Future sequence Seq(Future(cityTemp("Fescno")),
                                        Future(cityTemp("Tempe")))
    cityTemps onSuccess {
      case Seq(x, y) if x > y => println(s"Fresno is warmer: $x K")
      case Seq(x, y) if y > x => println(s"Tempe is warmer: $y K")
    }
    Thread sleep 3000

    //同步处理future,在大并发和高性能的应用中不推荐用
    //使用Await.result方法,传入一个后台线程和一个最大等待时间.
    //等待时间使用Duration类
    val maxTime = Duration(2, SECONDS)
    //把Try和Await.result结合使用
    Try(Await.result(nextFtr(5), maxTime)) match {
      case Success(x)  => print(x)
      case Failure(ex) => ex.printStackTrace
    }
    println("end")

  }
}
