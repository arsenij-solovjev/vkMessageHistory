package jinesra.vkMessageHistory

import scalax.file.Path
object IO {

  def readFromFile(path: String) = {
    Path.fromString(path).lines(includeTerminator = true).
      dropWhile { _.isEmpty }.
      takeWhile { _.nonEmpty }.     
      fold("")((wholeString, newLine) => wholeString ++ newLine)
  }

  def writeToFile(offset: Int, content: String) =
    {
      val p = Path.fromString("/home/arsenij/chatik/chatik_" + offset.toString).createFile(failIfExists = false)
      p.write(content)
    }
}