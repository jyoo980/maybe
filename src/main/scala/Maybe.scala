abstract class Maybe[T] {

  def isDefined: Boolean

  def get: T

  def exists(p: T => Boolean): Boolean

  def filter(p: T => Boolean): Maybe[T]

  def filterNot(p: T => Boolean): Maybe[T]

  def map[U](f: T => U): Maybe[U]

  def fold[U](ifEmpty: U)(f: T => U): U

  def getOrElse[U <: T](ifEmpty: U): T

  def flatMap[U](f: T => Maybe[U]): Maybe[U]

  def toList: List[T]
}
