abstract class Maybe[T] {

  /** Existential check for a Maybe[T] type
   *
   * @return true for Just[T], false otherwise
   */
  def isDefined: Boolean

  /** Evaluates to the unwrapped instance of T
   *
   * @return unwrapped instance of T for Just[T]
   * @throws NoSuchElementException if invoked on Nothing[T]
   */
  def get: T

  /** Existential check for a Maybe[T] type
   *
   *  Applies predicate function p against wrapped instance of T
   *
   * @param p predicate function with type T => Boolean
   * @return true if the wrapped instance of T exists and fulfills p
   */
  def exists(p: T => Boolean): Boolean

  /** Filter function for a Maybe[T] type
   *
   *  Evaluates to a Maybe[T] type where the wrapped instance of T fulfills
   *  the predicate function p
   *
   * @param p predicate function with type T => Boolean
   * @return Just[T] if the wrapped instance of T fulfills p, else Nothing[T]
   */
  def filter(p: T => Boolean): Maybe[T]

  /** Analogue to .filter for a Maybe[T] type
   *
   *  Evaluates to a Maybe[T] tye where the wrapped instance of T does NOT
   *  fulfill the predicate function p
   *
   * @param p predicate function with type T => Boolean
   * @return Just[T] if the wrapped instance of T does NOT fulfill p, else Nothing[T]
   */
  def filterNot(p: T => Boolean): Maybe[T]

  /** Mapping function from Maybe[T] => Maybe[U]
   *
   * @param f  mapping function with type T => U
   * @tparam U return type of function f
   * @return Maybe[U]
   */
  def map[U](f: T => U): Maybe[U]

  /** Fold function for Maybe[T] => U
   *
   * Note, this is analogous to a composition of .map and .getOrElse and
   * can also be expressed as an explicit pattern match on Just and Nothing
   * e.g.
   *  Just(2).map(_ + 2).getOrElse(-1)
   *  Just(2).fold(-1)(_ + 2)
   *  maybeNum match {
   *    case Just(n) => n + 2
   *    case _ => -1
   *  }
   *
   * @param ifEmpty evaluates to this if the Maybe[T] instance is a Nothing[T]
   * @param f mapping function from T => U
   * @tparam U return type of this fold operation
   * @return some instance of type U
   */
  def fold[U](ifEmpty: U)(f: T => U): U

  /** Offers an alternate instance of Maybe[U] in case this is an instance of Nothing[T]
   *
   * @param alternate evaluate to this in the case that this is an instance of Nothing[T]
   * @tparam U supertype of T
   * @return this Maybe[T] if it is an instance of Just[T], else offer alternate
   */
  def orElse[U >: T](alternate: Maybe[U]): Maybe[U]

  /** Unwrapping function for this Maybe[T]
   *
   * @param ifEmpty evaluates to this unwrapped instance of U if this is an instance of Nothing[T]
   * @tparam U subtype of T
   * @return the unwrapped T if this is an instance of Just[T], else offer ifEmpty
   */
  def getOrElse[U <: T](ifEmpty: U): T

  /** Identical to .map, except the mapping function produces the type Maybe[U]
   *
   * @param f mapping function from T => Maybe[U]
   * @tparam U type parameter of the mapping function f
   * @return an instance of Maybe[U], where the U is a result of a transformation from
   *         T => Maybe[U]
   */
  def flatMap[U](f: T => Maybe[U]): Maybe[U]

  /** Transformation from a Maybe[T] to a List[T]
   *
   * Recall that a Maybe[T] can be thought of as a unary collection
   * This conversion function makes that connection explicit
   *
   * @return a unary List[T] for a Just[T], an empty List[T] for a Nothing[T]
   */
  def toList: List[T]
}
