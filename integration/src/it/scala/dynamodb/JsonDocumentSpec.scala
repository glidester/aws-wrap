/*
 * Copyright 2015 Daniel W. H. James
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.dwhjames.awswrap.dynamodb

import org.scalatest.{FlatSpec, Matchers}
import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

import scala.concurrent.duration._


class JsonDocumentSpec
  extends FlatSpec
     with Matchers
     with DynamoDBClient
{

  import SampleData.sampleGameScores

  override val tableNames = Seq(GameScore.tableName)

  val mapper = AmazonDynamoDBScalaMapper(client)

  override def beforeAll(): Unit = {
    super.beforeAll()

    tryCreateTable(GameScore.tableRequest)
    awaitTableCreation(GameScore.tableName)

    await(30.seconds) {
      mapper.batchDump(sampleGameScores)
    }
  }

  "DynamoDB" should s"contain the '${GameScore.tableName}' table" in {
    val result = await(1.minutes) {
      client.listTables()
    }

    result.getTableNames().asScala should contain (GameScore.tableName)
  }

  it should s"contain ${sampleGameScores.size} game score items" in {
    await {
      mapper.countScan[GameScore]()
    } should equal (sampleGameScores.size)
  }

  it should s"insert new json version" in {

  }
}

object JsonDocumentSpec {
  val json = """
{
  "userId" : "901",
  "gameTitle" : "Galaxy Invaders",
  "topScore" : 5842,
  "topScoreDateTime" : "2016-12-21'T'12:12:12.123Z",
  "wins" : 21,
  "losses" : 72
}"""
}
