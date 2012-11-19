package aws.cloudsearch

import java.lang.{ Long => JLong, Double => JDouble, Boolean => JBool }

import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit._

import play.api.libs.ws.Response
import play.api.libs.json.util._
import play.api.libs.json._

import aws.core._
import aws.core.parsers._

object CloudSearchParsers {

  implicit def cloudSearchMetadataParser = Parser[CloudSearchMetadata] { r =>
    val read = (r.json \ "info").validate((
      (__ \ "rid").read[String] and
      (__ \ "time-ms").read[Long].map(Duration(_, MILLISECONDS)) and
      (__ \ "cpu-time-ms").read[Long].map(Duration(_, MILLISECONDS))
    )(CloudSearchMetadata))
    Success(read.get)
  }

  implicit def safeResultParser[T](implicit p: Parser[T]): Parser[Result[CloudSearchMetadata, T]] =
    Parser.xmlErrorParser[CloudSearchMetadata].or(Parser.resultParser(cloudSearchMetadataParser, p))
}