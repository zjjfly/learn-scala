package com.github.zjjfly.ls

import java.io.File
import java.nio.file.{Files, Paths}
import org.scalatest._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.JavaConverters.asScalaIteratorConverter
import scala.util.{Random, Try}

/**
  * Created by zjjfly on 16/4/29.
  */
//1
object HtmlUtil {
  def removeMarkup(input: String) = {
    Option(input)
      .map(
        _.replaceAll("(?s)<script.*</script>", "")
          .replaceAll("""</?\w[^>]*>""", "")
          .replaceAll("<.*>", ""))
      .getOrElse("")
  }
}

class HtmlUtilSpec extends AnyFlatSpec with Matchers {
  "The Html Utils object" should "remove single elements" in {
    HtmlUtil.removeMarkup("<br/>") should equal("")
  }

  it should "remove paired elements" in {
    HtmlUtil.removeMarkup("<b>Hi</b>") should equal("Hi")
  }
  //be用来比较全局对象,如Nil,true,false
  it should "have no effect on empty strings" in {
    val empty = true
    HtmlUtil.removeMarkup(null).isEmpty should be(empty)
  }

  it should "support mutilple lines" in {
    HtmlUtil.removeMarkup("""<html
        >
        <head/>
        <body>
        <p>Zjj</p>
        </body>
        </html>
      """).trim should equal("Zjj")
  }

  it should "remove script lines" in {
    HtmlUtil.removeMarkup("""<html
       >
       <head>
       <script type="text/javascript">
         console.log("Yo");
       </script>
       </head>
       <body>
       <p>Zjj</p>
       </body>
       </html>
      """).trim should equal("Zjj")
  }
}

trait SafeStringUtil {
  // Returns a trimmed version of the string wrapped in an Option,
  // or None if the trimmed string is empty.
  def trimToNone(s: String): Option[String] = {
    Option(s) map (_.trim) filterNot (_.isEmpty)
  }

  //1 c,d
  def parseToInt(s: String): Option[Int] = {
    trimToNone(s) flatMap (a => Try(a.toInt).toOption)
  }
  //1 e
  def randomLetters(size: Int): String = {
    val letters = ('A' to 'Z') ++ ('a' to 'z')
    (1 to size).map(_ => Random nextInt letters.size) map (letters) mkString ("")
  }
}

//创建一个特质的对象版本是很常用的扩展特质的实用性的方法
object SafeStringUtil extends SafeStringUtil

class SafeStringUtilSpec extends AnyFlatSpec with Matchers {
  "The com.github.zjjfly.ls.SafeStringUtil Object should" should "return a None when input string is empty" in {
    SafeStringUtil.trimToNone("") should be(None)
    SafeStringUtil.trimToNone(" ") should be(None)
    SafeStringUtil.trimToNone("       ") should be(None)
  }
  it should "handle a null input safely" in {
    SafeStringUtil.trimToNone(null) should be(None)
    SafeStringUtil.parseToInt(null) should be(None)
  }
  it should "trim the non-empty string" in {
    SafeStringUtil.trimToNone("zjj ") should equal(Some("zjj"))
  }

  it should "leave untrimmable strings alone" in {
    SafeStringUtil trimToNone "zjj" should equal(Some("zjj"))
  }

  it should "convert valid input string to integer" in {
    SafeStringUtil.parseToInt("134") should equal(Some(134))
  }

  it should "trim input string before convertion" in {
    SafeStringUtil.parseToInt(" 87") should equal(Some(87))
    SafeStringUtil.parseToInt("41 ") should equal(Some(41))
    SafeStringUtil.parseToInt(" 56 ") should equal(Some(56))
  }

  it should "convert invalid input string safely" in {
    SafeStringUtil.parseToInt("") should be(None)
    SafeStringUtil.parseToInt("7da") should be(None)
    SafeStringUtil.parseToInt("7 9") should be(None)
    SafeStringUtil.parseToInt("97&") should be(None)
  }

  it should "generate random strings with only lower- and upper-case letters" in {
    SafeStringUtil.randomLetters(200).replaceAll("[a-zA-Z]", "") should equal(
      "")
  }

  it should "be sufficiently random" in {
    val src = SafeStringUtil.randomLetters(100).toList.sorted
    val dest = SafeStringUtil.randomLetters(100).toList.sorted
    src should not equal dest
  }

  it should "handle invalid input" in {
    SafeStringUtil.randomLetters(-1) should equal("")
  }
}
class MultiReplacerSpec extends AnyFlatSpec with Matchers with BeforeAndAfterAll {
  import com.github.zjjfly.ls.chapt9.MultiReplacer._

  override protected def afterAll(): Unit = {
    deleteAllFile()
  }

  val content = "Twas brillig, and the slithy toves"

  "The MultiReplacer app" should "replace basic patterns" in {
    val testFile = newFile(content)
    replaceFilesContent("brill[^,]*",
                        "the night before xmas",
                        List(testFile.getName))
    readFile(testFile) should equal(
      "Twas the night before xmas, and the slithy toves")

    replaceFilesContent("the slithy.*",
                        "all thru the house",
                        List(testFile.getName))
    readFile(testFile) should equal(
      "Twas the night before xmas, and all thru the house")
  }

  it should "create a backup file before replacing text" in {
    val testFile = newFile(content)

    replaceFilesContent("brill[^,]*",
                        "the night before xmas",
                        List(testFile.getName))
    readFile(testFile) should equal(
      "Twas the night before xmas, and the slithy toves")

    val backupFile = new File(testFile.getName + ".bak")
    readFile(backupFile) should equal(content)
  }

  it should "create a backup file of any file" in {
    val testFile = newFile(content)
    createBackupFile(content, testFile)
    val backupFile = new File(testFile.getName + ".bak")
    readFile(backupFile) should equal(readFile(testFile))
  }

  it should "replace content in a file" in {
    val testFile = newFile(content)

    replaceFilesContent("Twas brilli",
                        "I was sleepin",
                        List(testFile.getAbsolutePath))
    readFile(testFile) should equal("I was sleeping, and the slithy toves")
  }

  it should "replace content in a series of files by file name" in {
    val testFile1 = newFile(content)
    val testFile2 = newFile(content)

    val files = List(testFile1.getName, testFile2.getName)
    replaceFilesContent("Twas", "Twasn't", files)
    readFile(testFile1) should equal("Twasn't brillig, and the slithy toves")
    readFile(testFile2) should equal("Twasn't brillig, and the slithy toves")
  }

  private def newFile(content: String): File = {
    val testFile = new File(s"testy_${SafeStringUtil.randomLetters(20)}.txt")
    writeFile(testFile, content)
    testFile
  }
  private def deleteAllFile(): Unit = {
    val path = Paths.get(".")
    Files
      .walk(path)
      .iterator()
      .asScala
      .map(_.getFileName)
      .filter(_.toString.startsWith("testy_"))
      .foreach(Files.delete(_))
  }
}
