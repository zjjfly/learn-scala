package com.github.zjjfly.ls.utils

import java.io.File

/**
  * Created by zjjfly on 16/5/4.
  */
object FileUtil {
  def readFile(f: File): String = {
    io.Source.fromFile(f).getLines().mkString("\n")
  }
}
