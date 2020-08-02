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

  def fold[U](ifEmpty: U)(f: T => U): U

  def orElse[U >: T](alternate: Maybe[U]): Maybe[U]

  def getOrElse[U <: T](ifEmpty: U): T

  def flatMap[U](f: T => Maybe[U]): Maybe[U]

  def toList: List[T]
}
