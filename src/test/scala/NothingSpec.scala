import java.util.NoSuchElementException

import org.scalatest.matchers._
import org.scalatest.flatspec.AnyFlatSpec

class NothingSpec extends AnyFlatSpec with should.Matchers
  with MaybeFixture {

  "Nothing" should "not be defined" in {
    val none = toNothing
    none.isDefined shouldBe false
  }

  it should "throw a NoSuchElementException when .get is called" in {
    val none = toNothing
    try {
      none.get
      fail("calling .get on Nothing failed to throw a NoSuchElementException")
    } catch {
      case _: NoSuchElementException => succeed
      case e: Throwable => fail(s"calling .get on Nothing yielded: $e")
    }
  }

  it should "not evaluate to true for .exists" in {
    val none = toNothing
    none.exists(_ == 1) shouldBe false
  }

  it should "have no effect when .filter is called" in {
    val none = toNothing[1]
    none.filter(_ < 1) shouldBe Nothing()
  }

  it should "have no effect when .filterNot is called" in {
    val none = toNothing[String]
    none.filterNot(_ == "apple") shouldBe Nothing()
  }

  it should "evaluate to the alternate when .orElse is called" in {
    val alternate = toJust(22)
    val none = toNothing[Int]
    none.orElse(alternate) match {
      case Just(num) => num shouldBe 22
      case _ => fail("calling .orElse on Nothing should not have yielded a Nothing given a Just[T]")
    }
  }

  it should "evaluate to Nothing when .flatMap is called" in {
    def f(x: String): Maybe[String] =
      Just(x * 3)
    val none = toNothing[String]
    none.flatMap(f) match {
      case Just(_) => fail("calling .flatMap on Nothing should not have yielded Just[T]")
      case Nothing() => succeed
    }
  }

  it should "be able to be converted to an empty List[T] instance" in {
    val none = toNothing[Int]
    none.toList match {
      case Nil => succeed
      case _ => fail(".toList on a Nothing should have been converted to an empty List[T]")
    }
  }
}


