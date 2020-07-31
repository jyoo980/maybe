final case class Nothing[T]() extends Maybe[T] {

  override def isDefined: Boolean = false

  override def get: T =
    throw new NoSuchElementException

  override def exists(p: T => Boolean): Boolean = false

  override def filter(p: T => Boolean): Maybe[T] = Nothing[T]()

  override def filterNot(p: T => Boolean): Maybe[T] = Nothing[T]()

  override def map[U](f: T => U): Maybe[U] = Nothing[U]()

  override def fold[U](ifEmpty: U)(f: T => U): U = ifEmpty
}
