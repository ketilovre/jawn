package jawn

import java.io.File
import java.nio.ByteBuffer
import java.nio.channels.ReadableByteChannel
import scala.util.Try

trait SupportParser[J] {
  implicit def facade: Facade[J]

  def parseUnsafe(s: String): J =
    new StringParser(s).parse()

  def parseFromString(s: String): Try[J] =
    Try(new StringParser[J](s).parse)

  def parseFromPath(file: File): Try[J] =
    Try(ChannelParser.fromFile[J](file).parse)

  def parseFromFile(file: File): Try[J] =
    Try(ChannelParser.fromFile[J](file).parse)

  def parseFromChannel(ch: ReadableByteChannel): Try[J] =
    Try(ChannelParser.fromChannel[J](ch).parse)

  def parseFromByteBuffer(buf: ByteBuffer): Try[J] =
    Try(new ByteBufferParser[J](buf).parse)
}