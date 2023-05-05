package com.github.zjjfly.ls.chapt7

import scala.io.Source
import scala.language.postfixOps

/**
  * Created by zjjfly on 16/4/7.
  */
object Exercise2 {
  def main(args: Array[String]) {
    def gitRss(user: String, repo: String, branch: String): String = {
      val url = s"https://github.com/$user/$repo/commits/$branch.atom"
      val lines = Source.fromURL(url).getLines().toList
      val xml = lines.map(_.trim).mkString("")
      xml
    }

    def xml2EntryList(xml: String) =
      xml.split("</?entry>").filterNot(_ isEmpty).tail.toList

    def element(x: String, name: String): Option[String] = {
      val p = s".*<$name>(.*)</$name>.*".r
      x match {
        case p(re) => Option(re)
        case _     => None
      }
    }

    def report(entryXml: String): Option[String] = {
      for {
        title <- element(entryXml, "title")
        date <- element(entryXml, "updated").map(_.replaceAll("T.*", ""))
        author <- element(entryXml, "name")
      } yield s"title:  $title\ndate:   $date\nauthor: $author"
    }

    def getGitHubReport(user: String, repo: String, branch: String): String = {
      val xml: String = gitRss(user, repo, branch)
      val entrys: List[String] = xml2EntryList(xml)
      val formatted: List[String] = entrys flatMap report
      val title = s"Github commit activity for $repo:$branch"
      title :: formatted mkString ("\n" + "=" * 80 + "\n")
    }
    //val web: String = getGitHubReport("zjjfly", "Web", "master")
    //println(web)
    //a)
    def getGithubReport(urb: (String, String, String)): String = {
      val xml = gitRss(urb._1, urb._2, urb._3)
      val entries = xml2EntryList(xml)
      val formattedEntries = entries flatMap report
      val title = s"Github commit activity for ${urb._2}:${urb._3}"
      title :: formattedEntries mkString ("\n" + "=" * 80 + "\n")
    }
    val slickUrb = ("slick", "slick", "master")
    //println(getGithubReport(slickUrb))

    //b)
    def getGithubReports(urbs: List[(String, String, String)]) =
      urbs map getGithubReport
    val akkaUrb = ("akka", "akka", "master")
    //println(getGithubReports(List(akkaUrb,slickUrb)) mkString "\n"+"*"*80+"\n")

    //d)
    def getGithubReportsAsync(urbs: List[(String, String, String)]): String = {
      val commits = List.newBuilder[String]

      import concurrent.ExecutionContext.Implicits.global
      val futures = urbs map { urb =>
        concurrent.Future {
          commits ++= getGithubCommitReports(urb)
        }
      }
      val future = concurrent.Future.sequence(futures)

      import concurrent.duration._
      concurrent.Await.result(future, Duration(20, SECONDS))

      val separator = "\n" + "*" * 80 + "\n"
      val title =
        s"Github activity for ${urbs map (_._1) mkString (", ")} repos"
      (title :: commits.result) mkString separator
    }
    //    println(getGithubReportsAsync(List(akkaUrb, slickUrb)))

    //d
    def getGithubCommitReports(urb: (String, String, String)): List[String] = {
      val xml = gitRss(urb._1, urb._2, urb._3)
      val entries = xml2EntryList(xml)
      val branchInfo = s"branch: ${urb._2}:${urb._3}\n"
      entries flatMap report map (branchInfo + _)
    }

    def getGithubReportsSync(urbs: List[(String, String, String)]): String = {
      val commits = List.newBuilder[String]

      import concurrent.ExecutionContext.Implicits.global
      val futures = urbs map { urb =>
        concurrent.Future {
          commits ++= getGithubCommitReports(urb)
        }
      }
      val future = concurrent.Future.sequence(futures)

      import concurrent.duration._
      concurrent.Await.result(future, Duration(20, SECONDS))

      val separator = "\n" + "*" * 80 + "\n"
      val title =
        s"Github activity for ${urbs map (_._1) mkString (", ")} repos"
      val sortedCommits = commits.result.sortBy { c =>
        c.replaceAll("(?s).*date:   ", "").replaceAll("(?s)\\s.*", "")
      }.reverse
      (title :: sortedCommits) mkString separator
    }
    val zjj: (String, String, String) = ("zjjfly", "Web", "master")
    println(getGithubReportsSync(List(zjj, akkaUrb)))

  }
}
