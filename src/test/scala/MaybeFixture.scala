trait MaybeFixture {

  def toJust[T](t: T): Maybe[T] = Just[T](t)

  def toNothing[T]: Maybe[T] = Nothing[T]()
}
