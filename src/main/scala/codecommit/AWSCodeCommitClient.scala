/*
 * Copyright 2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.github.dwhjames.awswrap
package codecommit

import java.util.concurrent.{Executors, ExecutorService}

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.AWSCredentials
import com.amazonaws.services.codecommit.AWSCodeCommitAsync
import com.amazonaws.services.codecommit.AWSCodeCommitAsyncClient
import com.amazonaws.services.codecommit.model._

/** A Scala-friendly wrapper around an {@code AWSCodeCommitAsync}. */
class AWSCodeCommitClient(private val client: AWSCodeCommitAsync) {

  /**
   * Create a client specifying all parameters
   *
   * @param awsCredentials the credentials used to connect
   * @param clientConfiguration the client configuration (default used if not specified)
   * @param executorService the proposed executor service (default thread pool size of 50 used if not specified)
   * @param region the region (default US_EAST_1 used if not specified)
   * @return
   */
  def this(awsCredentials: AWSCredentials,  clientConfiguration: ClientConfiguration = new ClientConfiguration(), executorService: ExecutorService = Executors.newFixedThreadPool(50), region: com.amazonaws.regions.Region = com.amazonaws.regions.RegionUtils.getRegion("us-east-1")) =
    this({
      val client = new AWSCodeCommitAsyncClient(awsCredentials,clientConfiguration,executorService)
      client.setRegion(region)
      client
    })

  /** Creates a client for the given region.
    *
    * @param region the region
    */
  def this(region: com.amazonaws.regions.Region) =
    this({
      val client = new AWSCodeCommitAsyncClient()
      client.setRegion(region)
      client
    })

  /** Creates a client for the given region.
    *
    * @param region the region
    */
  def this(region: com.amazonaws.regions.Regions) =
    this(com.amazonaws.regions.Region.getRegion(region))

  /** Creates a client for the given region.
    *
    * @param region the region
    */
  def this(region: String) =
    this(com.amazonaws.regions.RegionUtils.getRegion(region))

  /** Invokes the {@code getBranchAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def getBranch(request: GetBranchRequest):
      scala.concurrent.Future[GetBranchResult] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[GetBranchResult]

    client.getBranchAsync(request, new com.amazonaws.handlers.AsyncHandler[GetBranchRequest, GetBranchResult]() {
      override def onSuccess(request: GetBranchRequest, result: GetBranchResult) = {promise.success(result); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code deleteRepositoryAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def deleteRepository(request: DeleteRepositoryRequest):
      scala.concurrent.Future[DeleteRepositoryResult] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[DeleteRepositoryResult]

    client.deleteRepositoryAsync(request, new com.amazonaws.handlers.AsyncHandler[DeleteRepositoryRequest, DeleteRepositoryResult]() {
      override def onSuccess(request: DeleteRepositoryRequest, result: DeleteRepositoryResult) = {promise.success(result); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code createBranchAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def createBranch(request: CreateBranchRequest):
      scala.concurrent.Future[scala.runtime.BoxedUnit] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[scala.runtime.BoxedUnit]

    client.createBranchAsync(request, new com.amazonaws.handlers.AsyncHandler[CreateBranchRequest, Void]() {
      override def onSuccess(request: CreateBranchRequest, result: Void) = {promise.success(scala.runtime.BoxedUnit.UNIT); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code listBranchesAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def listBranches(request: ListBranchesRequest):
      scala.concurrent.Future[ListBranchesResult] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[ListBranchesResult]

    client.listBranchesAsync(request, new com.amazonaws.handlers.AsyncHandler[ListBranchesRequest, ListBranchesResult]() {
      override def onSuccess(request: ListBranchesRequest, result: ListBranchesResult) = {promise.success(result); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code listRepositoriesAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def listRepositories(request: ListRepositoriesRequest):
      scala.concurrent.Future[ListRepositoriesResult] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[ListRepositoriesResult]

    client.listRepositoriesAsync(request, new com.amazonaws.handlers.AsyncHandler[ListRepositoriesRequest, ListRepositoriesResult]() {
      override def onSuccess(request: ListRepositoriesRequest, result: ListRepositoriesResult) = {promise.success(result); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code getRepositoryAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def getRepository(request: GetRepositoryRequest):
      scala.concurrent.Future[GetRepositoryResult] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[GetRepositoryResult]

    client.getRepositoryAsync(request, new com.amazonaws.handlers.AsyncHandler[GetRepositoryRequest, GetRepositoryResult]() {
      override def onSuccess(request: GetRepositoryRequest, result: GetRepositoryResult) = {promise.success(result); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code batchGetRepositoriesAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def batchGetRepositories(request: BatchGetRepositoriesRequest):
      scala.concurrent.Future[BatchGetRepositoriesResult] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[BatchGetRepositoriesResult]

    client.batchGetRepositoriesAsync(request, new com.amazonaws.handlers.AsyncHandler[BatchGetRepositoriesRequest, BatchGetRepositoriesResult]() {
      override def onSuccess(request: BatchGetRepositoriesRequest, result: BatchGetRepositoriesResult) = {promise.success(result); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code updateDefaultBranchAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def updateDefaultBranch(request: UpdateDefaultBranchRequest):
      scala.concurrent.Future[scala.runtime.BoxedUnit] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[scala.runtime.BoxedUnit]

    client.updateDefaultBranchAsync(request, new com.amazonaws.handlers.AsyncHandler[UpdateDefaultBranchRequest, Void]() {
      override def onSuccess(request: UpdateDefaultBranchRequest, result: Void) = {promise.success(scala.runtime.BoxedUnit.UNIT); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code updateRepositoryDescriptionAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def updateRepositoryDescription(request: UpdateRepositoryDescriptionRequest):
      scala.concurrent.Future[scala.runtime.BoxedUnit] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[scala.runtime.BoxedUnit]

    client.updateRepositoryDescriptionAsync(request, new com.amazonaws.handlers.AsyncHandler[UpdateRepositoryDescriptionRequest, Void]() {
      override def onSuccess(request: UpdateRepositoryDescriptionRequest, result: Void) = {promise.success(scala.runtime.BoxedUnit.UNIT); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code updateRepositoryNameAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def updateRepositoryName(request: UpdateRepositoryNameRequest):
      scala.concurrent.Future[scala.runtime.BoxedUnit] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[scala.runtime.BoxedUnit]

    client.updateRepositoryNameAsync(request, new com.amazonaws.handlers.AsyncHandler[UpdateRepositoryNameRequest, Void]() {
      override def onSuccess(request: UpdateRepositoryNameRequest, result: Void) = {promise.success(scala.runtime.BoxedUnit.UNIT); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Invokes the {@code createRepositoryAsync} method of the underlying
    * client and adapts the result to a Scala {@code Future}.
    *
    * @param request the request to send
    * @return the future result
    */
  def createRepository(request: CreateRepositoryRequest):
      scala.concurrent.Future[CreateRepositoryResult] = {

    val opts = request.getRequestClientOptions()
    if (opts.getClientMarker(com.amazonaws.RequestClientOptions.Marker.USER_AGENT) == null) {
      opts.appendUserAgent("aws-scala-sdk")
    }

    val promise = scala.concurrent.Promise[CreateRepositoryResult]

    client.createRepositoryAsync(request, new com.amazonaws.handlers.AsyncHandler[CreateRepositoryRequest, CreateRepositoryResult]() {
      override def onSuccess(request: CreateRepositoryRequest, result: CreateRepositoryResult) = {promise.success(result); ()}
      override def onError(exception: Exception) = {promise.failure(exception); ()}
    })

    promise.future
  }

  /** Shuts down this client. */
  def shutdown(): Unit = client.shutdown()
}
