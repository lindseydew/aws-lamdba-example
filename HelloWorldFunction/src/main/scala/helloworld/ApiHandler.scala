package helloworld

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import com.amazonaws.services.lambda.runtime.events.{APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent}

import scala.jdk.CollectionConverters._

object ApiHandler {

  /**
   * Handle a Lambda request indirectly via the API Gateway
   * @param request the Java HTTP request
   * @param context the Java Lambda context
   * @return the HTTP response
   */
  def handle(request: APIGatewayProxyRequestEvent, context: Context): Response = {
    println("handling %s %s, remaining time is %d ms".format(
      request.getHttpMethod, request.getPath, context.getRemainingTimeInMillis) )

    println(s"""environment = ${sys.env.getOrElse("env", "n/a")}""")
    val name = request.getPathParameters.get("name")
    val queryString = Option(request.getQueryStringParameters).map(_.asScala)
    val fooParam = queryString.flatMap(_.get("foo"))
    Response(s"Hello ${name}. ${fooParam.fold("no query strings")(param => s"Your query string is ${param}")} ", Map("Content-Type" -> "text/plain"))
  }

  case class Response(body: String, headers: Map[String,String], statusCode: Int = 200) {
    lazy val javaHeaders: java.util.Map[String, String] = headers.asJava
  }


}