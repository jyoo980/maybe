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
      case e: NoSuchElementException => succeed
      case other => fail(s"calling .get on Nothing yielded: $other")
    }
  }
}


