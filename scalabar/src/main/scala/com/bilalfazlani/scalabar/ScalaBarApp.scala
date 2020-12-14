package com.bilalfazlani.scalabar

import com.bilalfazlani.scalabar.models._
import com.bilalfazlani.scalabar.models.MenuItem._
import com.bilalfazlani.scalabar.parser.{Parser, Renderer}
import com.bilalfazlani.scalabar.dsl._

import java.util.Base64

type Handler = PartialFunction[(String, Option[String]), Unit]

abstract class ScalaBarApp {
  val pluginName: String

  val appMenu: MenuBuilder
  val handler: HandlerBuilder
  val tags: TagBuilder

  private def decode(str: String) = new String(Base64.getDecoder.decode(str))

  def main(args: Array[String]): Unit = {
    tags.build.foreach(println)
    args.toList match {
      case "dispatch" :: action :: Nil =>
        handler.build()(decode(action), None)
      case "dispatch" :: action :: metadata :: Nil =>
        handler.build()(decode(action), Some(decode(metadata)))
      case _ =>
        new Parser(new Renderer(new SelfPath(pluginName)))
          .parse(appMenu.build)
          .lines
          .foreach(println)
    }
  }
}
