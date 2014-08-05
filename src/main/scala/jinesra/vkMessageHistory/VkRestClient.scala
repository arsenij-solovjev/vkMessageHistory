package jinesra.vkMessageHistory
import com.typesafe.scalalogging._
import com.typesafe.scalalogging.slf4j._

import scala.xml._
import scala.util.{ Success, Failure }
import java.io.InputStreamReader
import net.liftweb.json._
import scalaj.http._
import scalax.file.Path
import scalaj.http.Http.Request

class VkRestClient(accessToken: String) extends LazyLogging {
  val apiHost = "https://api.vk.com/"

  val chatHistoryMethod = "method/messages.getHistory"
  val getUserMethod = "method/users.get"

  def getMessageHistory(offset: Int, chat_id: Int, count: Int):JValue=
    {
      val req = Http(apiHost + chatHistoryMethod).
        option(HttpOptions.connTimeout(10000)).
        option(HttpOptions.readTimeout(50000)).
        params(
          ("offset", offset.toString),
          ("count", count.toString),
          ("chat_id", chat_id.toString),
          ("rev", "1"),
          ("access_token", accessToken))

      requestToJson(req)
    }
  
  def getUser(userId: Int):JValue =
    {
      val req = Http(apiHost + getUserMethod).
        option(HttpOptions.connTimeout(10000)).
        option(HttpOptions.readTimeout(50000)).
        params(
          ("user_ids", userId.toString))

      requestToJson(req)
    }
  
  def requestToJson(req: Request) = req {
    is => JsonParser.parse(new InputStreamReader(is))
  }

  def getFromIds(json: JObject) = (json \\ "from_id").children.map(x => x.extract[String])

  def writeToFile(offset: Int, content: String) =
    {
      val p = Path.fromString("/home/arsenij/chatik/chatik_" + offset.toString).createFile(failIfExists = false)
      p.write(content)
    }

}
