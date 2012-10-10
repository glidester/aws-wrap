package aws.dynamodb

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import play.api.libs.ws._
import play.api.libs.ws.WS._
import play.api.libs.json._

import aws.core._
import aws.core.Types._
import aws.core.parsers._
import aws.core.utils._
import aws.core.signature._

import aws.dynamodb.models._
import aws.dynamodb.DDBParsers._

object DynamoDB {

  import aws.dynamodb._

  import DDBRegion.DEFAULT

  private def request(operation: String,
                      body: JsValue)(implicit region: AWSRegion): Future[Response] = {
    val requestTime = new java.util.Date()
    val headers = Seq(
      "host" -> region.host,
      "x-amz-date" -> AWS.isoDateFormat(requestTime),
      "x-amz-target" -> ("DynamoDB_" + V4.VERSION + "." + operation),
      "Content-Type" -> "application/x-amz-json-1.0")
    val allHeaders = headers ++ Seq(
      "Authorization" -> V4.authorizationHeader(requestTime, headers, Nil, body.toString))

    WS.url("https://" + region.host + "/").withHeaders(allHeaders: _*).post(body.toString)
  }

  private def tryParse[T](resp: Response)(implicit p: Parser[SimpleResult[T]]) = Parser.parse[SimpleResult[T]](resp).fold(
    e => throw new RuntimeException(e),
    identity
  )

  private def post[T](operation: String,
                      body: JsValue,
                      jsonPart: JsValue => JsValue = identity)(implicit p: Parser[T], region: AWSRegion): Future[SimpleResult[T]] = {
    request(operation, body).map(r => tryParse[T](r))
  }

  def listTables(limit: Option[Int] = None,
                 exclusiveStartTableName: Option[String] = None)(implicit region: AWSRegion): Future[SimpleResult[Seq[String]]] = {
    val data = (
      limit.map("Limit" -> Json.toJson(_))
      ++ exclusiveStartTableName.map("ExclusiveStartTableName" -> Json.toJson(_))).toMap
    post[Seq[String]]("ListTables", Json.toJson(data), _ \ "TableNames")
  }

  def createTable(name: String,
                  keySchema: KeySchema,
                  provisionedThroughput: ProvisionedThroughput)(implicit region: AWSRegion): Future[SimpleResult[TableDescription]] = {
    val body = Json.obj(
      "TableName" -> name,
      "KeySchema" -> keySchema,
      "ProvisionedThroughput" -> provisionedThroughput
    )
    post[TableDescription]("CreateTable", body, _ \ "TableDescription")
  }

  def deleteTable(name: String)(implicit region: AWSRegion): Future[EmptySimpleResult] = {
    val body = Json.obj("TableName" -> name)
    post[Unit]("DeleteTable", body)
  }

  def describeTable(name: String)(implicit region: AWSRegion): Future[SimpleResult[TableDescription]] = {
    val body = JsObject(Seq("TableName" -> JsString(name)))
    post[TableDescription]("DescribeTable", body, _ \ "Table")
  }

  // DeleteItem

  // GetItem

  // PutItem

  // Query

  // Scan

  // UpdateItem

  // UpdateTable

  // BatchGetItem

  // BatchWriteItem

}
