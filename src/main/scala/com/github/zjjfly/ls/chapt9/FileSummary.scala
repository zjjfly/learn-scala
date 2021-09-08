package com.github.zjjfly.ls.chapt9

import java.io.File
import com.github.zjjfly.ls.utils.FileUtil._

/**
  * Created by zjjfly on 16/5/3.
  */
case class Summary(chars: Int,
                   words: Int,
                   paragraph: Int,
                   topWords: List[String])

trait StringSummary {
  def summary(s: String): Summary = {
    val words = s.split("""\W+""")
    val paragraphs = s.split("""\W+\s?\n+""")
    val topWords: List[String] = words
      .map(_.toLowerCase)
      .groupBy(w => w)
      .toList
      .sortBy(_._2.size)
      .reverse
      .map(_._1)
      .take(20)
    Summary(s.size, words.size, paragraphs.size, topWords)
  }

  def format(summary: Summary): String = {
    import summary._
    val formatted =
      s"""$chars chars, $words words and $paragraph paragraphs.
The top 20 words were: ${topWords.mkString(", ")}."""
    formatted
      .replaceAll("\n", " ")
      .replaceAll(", ([^,]*)$", ", and $1")
  }
}

class FileSummary(f: File) extends StringSummary {
  def doSummary: Unit = {
    val content: String = readFile(f).trim
    val s: Summary = summary(content)
    println(format(s))
  }
}

object FileSummary {
  def main(args: Array[String]): Unit = {
//    new FileSummary(new File("/Users/zjjfly/Desktop/1.txt")).doSummary
    val columns = formatFixedWidthColumns(
      List("Id", "Title", "User", "Comments", "Labels"))
    println(columns mkString ("|", "|", "|"))
  }

  def formatFixedWidthColumns(cols: List[String]): List[String] = {
    if (cols.size < 5) cols
    else
      List(
        //-7表示在插入的字符串以及之后的位置共七个字符位,如果是7,则表示插入字符和之前的位置共七个字符.如果插入的字符不满7,则其他的位置用空格填补
        //.7表示插入的字符的最大长度是7,如超过7,则值取前面七个字符
        f"${cols(0)}%-7.7s",
        f"${cols(1)}%-70.4s",
        f"${cols(2)}%-15.15s",
        f"${cols(3)}%-9.9s",
        f"${cols(4)}%-20.20s"
      )
  }
}
