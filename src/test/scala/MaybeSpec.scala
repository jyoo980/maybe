import org.scalatest.matchers._
import org.scalatest.flatspec.AnyFlatSpec

class MaybeSpec extends AnyFlatSpec with should.Matchers
  with MaybeFixture {

  "a Just[T]" should "be defined" in {
    val maybeNum = toJust(1)
    maybeNum.isDefined shouldBe true
  }

  it should "be defined in a pattern-match" in {
    val maybeStr = toJust("apple")
    maybeStr match {
      case Just(str) => str shouldBe "apple"
      case Nothing() => fail()
    }
  }

  it should "be map-able" in {
    val maybeStr = toJust("a")
    maybeStr.map(_ * 3) shouldBe Just("aaa")
  }

  it should "be fold-able" in {
    val maybeNum = toJust(123)
    maybeNum.fold("was empty")(_.toString) shouldBe "123"
  }

  it should "not throw a NoSuchElement exception when .get is called" in {
    val maybeStr = toJust("a")
    maybeStr.get shouldBe "a"
  }
}

sealed trait MaybeFixture {

  def toJust[T](t: T): Maybe[T] = Just[T](t)
}
