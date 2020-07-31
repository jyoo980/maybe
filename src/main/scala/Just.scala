final case class Just[T](x: T) extends Maybe[T] {

  override def isDefined: Boolean = true

  override def get: T = x

  override def exists(p: T => Boolean): Boolean = p(x)

  override def filter(p: T => Boolean): Maybe[T] =
    if (p(x)) Just(x)
    else Nothing[T]()

  override def filterNot(p: T => Boolean): Maybe[T] =
    if (!p(x)) Just(x)
    else Nothing[T]()

  override def map[U](f: T => U): Maybe[U] = Just(f(x))

  override def fold[U](ifEmpty: U)(f: T => U): U = f(x)
}
