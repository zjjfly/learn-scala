package com.github.zjjfly.ls.chapt9

import java.io.{PrintWriter, File}

/**
  * Created by zjjfly on 16/4/30.
  */
object MultiReplacer {
  def main(args: Array[String]) {
//    args.toList match {
//      case pattern :: replace :: files => {
//        println("seach pattern:" + pattern)
//        println("replacement text:" + replace)
//        println("files to process:" + files)
//        replaceFilesContent(pattern, replace, files)
//      }
//      case _ => {
//        println("Usage: MultiReplacer <search pattern> <replacement text> file1 [file2...]")
//      }
//    }
    val s =
      """
       |The fire is slowly dying,
       |And my dear, we're still good-by-ing.
       |But, as long as you love me so,
       |Let It Snow! Let It Snow! Let It snow
|
       |{}
|
       |Oh, it doesn't show signs of stopping,
       |And I've brought some corn for popping,
       |Since the lights are turned way down low,
       |Let It Snow! Let It Snow! Let It Snow!
      """.stripMargin
    val toList: List[String] = s.trim.split("""\W+\s+\n+""").toList
    toList.map(_.trim).foreach(println)
  }

  def readFile(file: File): String =
    io.Source.fromFile(file).getLines().mkString("\n")

  def replaceFilesContent(pattern: String,
                          replace: String,
                          files: List[String]) = {
    val validFiles: List[File] = files map (new File(_)) filter (_.exists())
    for (file <- validFiles) {
      val content: String = readFile(file)
      createBackupFile(content, file)
      val replaced: String = content.replaceAll(pattern, replace)
      writeFile(file, replaced)
      println(replaced)
    }
  }

  def writeFile(f: File, s: String) = {
    val writer = new PrintWriter(f)
    writer.write(s)
    writer.close()
  }

  def createBackupFile(s: String, file: File): Unit = {
    val dir = new File(file.getAbsoluteFile.getParent)

    var backupFile = new File(dir, s"${file.getName}.bak")
    while (backupFile.exists()) {
      backupFile =
        new File(dir, s"${file.getName}_${System.currentTimeMillis()}.bak")
    }
    writeFile(backupFile, s)
  }
}
