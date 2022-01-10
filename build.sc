import mill._
import scalalib._

object main extends ScalaModule {
  def scalaVersion = "3.1.0"
  def ivyDeps = Agg(
    ivy"io.netty:netty-all:4.1.72.Final"
  )
}
