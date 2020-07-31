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
      case e => fail(s"calling .get on Nothing yielded: $e")
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
}


