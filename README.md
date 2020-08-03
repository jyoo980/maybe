# maybe

This is a barebones implementation of Scala's own `Option[T]` library type, conveniently named `Maybe[T]`. The name comes from the [maybe monad](https://en.wikibooks.org/wiki/Haskell/Understanding_monads/Maybe), which also happens to be the the name of the type that is analogous to `Option[T]` in Haskell.

## Why Maybe?

Optional types provide an ergonomic way to enforce null-safety in many languages. The burden of remembering to check whether some value is null or not before performing some computation on it is lifted from the programmer to the compiler (which then bugs the programmer to explicitly handle it). Without an optional type, we could have the following disaster 

```Java
public int sneakyFactorial(int n) {
  if (n > 10) {
    return null;
  }
  if (n == 0) {
    return 1;
  }
  return n * sneakyFactorial(n - 1);
}

int someBigNum = sneakyFactorial(11);
// using someBigNum might lead to a NullPointerException if you
// don't check for null, the compiler also might not warn you.
```

We can rewrite the method above as a function that uses the `Maybe[T]` type in Scala
```Scala
def sneakyFactorial(n: Int): Maybe[Int] =
  if (n > 10) {
    Nothing()
  } else if (n == 1) {
    Just(1)
  } else {
    sneakyFactorial(n - 1).map(_ * n)
  }

val maybeSomeBigNum = sneakyFactorial(11)

// using maybeSomeBigNum without destructuring it into a Just[T] or a Nothing[T] will
// cause a compiler error
maybeSomeBigNum * 2 // compiler error

// Use explicit pattern matching
maybeSomeBigNum match {
  case Just(n) => n * 2 // this is fine
  case Nothing() => // handle the "null" case here
}
```

Note that we have access to _all_ the standard functions that are able to be called on sequences. This is because
one can effectively think of a `Maybe[T]` as a unary list

```Scala
// fine
maybeSomeBigNum.map(_ * 2).getOrElse(-1)

// also fine
maybeSomeBigNum.fold(-1)(_ * 2)
```

This is a rather contrived use case, but it demonstrates the safety gained from replacing nullable values with a
`Maybe[T]` type. It can effectively eliminate a class of runtime errors caused by null pointers by forcing a check
at compile-time to make sure they are handled.

## Dependencies
* [Scala 2.12+](https://www.scala-lang.org/download/)
* [sbt (Scala Build Tool)](https://www.scala-sbt.org/)
* [IntelliJ IDEA + Scala Plugin](https://docs.scala-lang.org/getting-started/intellij-track/getting-started-with-scala-in-intellij.html)
  * this is optional if you have `sbt` installed

## Working with maybe
* `sbt compile` to compile the project
* `sbt test` to run all tests under the `test` directory
* `sbt clean` to remove generated files from the compile step

