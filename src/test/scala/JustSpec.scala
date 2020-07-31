import org.scalatest.matchers._
import org.scalatest.flatspec.AnyFlatSpec

class JustSpec extends AnyFlatSpec with should.Matchers
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
    maybeStr.get match {
      case s: String => s shouldBe "a"
      case _ => fail("Calling .get on a Just should not throw an exception")
    }
  }

  it should "filter correctly for a given predicate that its inner value fulfills" in {
    val maybeString = toJust("a")
    maybeString.filter(_ == "a") shouldBe Just("a")
  }

  it should "filter correctly for a given predicate that its inner value does not fulfill" in {
    val maybeNum = toJust(1)
    maybeNum.filter(_ != 1) shouldBe Nothing()
  }

  it should "filterNot correctly for a given predicate that its inner value fulfills" in {
    val maybeNum = toJust(11)
    maybeNum.filterNot(_ == 11) shouldBe Nothing()
  }

  it should "filterNot correctly for a given predicate that its inner value does not fulfill" in {
    val maybeNum = toJust("a")
    maybeNum.filterNot(_ != "a") shouldBe Just("a")
  }

  it should "exist for a given predicate that its inner value fulfills" in {
    val maybeStr = toJust("a")
    maybeStr.exists(_ == "a") shouldBe true
  }

  it should "not fail when .getOrElse is called" in {
    val maybeNum = toJust(1)
    maybeNum.getOrElse(-1) shouldBe 1
  }

  it should "be able to chain calls" in {
    val maybeNum = toJust(123)
    maybeNum.map(_.toString).getOrElse("-1") shouldBe "123"
  }
}

sealed trait MaybeFixture {

  def toJust[T](t: T): Maybe[T] = Just[T](t)
}
