import org.scalatest.matchers._
import org.scalatest.flatspec.AnyFlatSpec

class JustSpec extends AnyFlatSpec with should.Matchers with MaybeFixture {

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

  it should "evaluate to a Just[T] value when .orElse is called on it" in {
    val alternate = toJust(-1)
    val maybeNum = toJust(1)
    maybeNum.orElse(alternate) match {
      case Just(-1) => fail(".orElse should not evaluate to its alternate value on a Just[T]")
      case Just(num) => num shouldBe 1
    }
  }

  it should "evaluate to a Just[T] value when .flatMap is called on it" in {
    def f(x: Int): Maybe[Int] =
      Just(x + 1)
    val maybeNum = toJust(1)
    maybeNum.flatMap(f) match {
      case Just(n) => n shouldBe 2
      case _ => fail(".flatMap failed to produce a Just[T] value")
    }
  }

  it should "not fail when .getOrElse is called" in {
    val maybeNum = toJust(1)
    maybeNum.getOrElse(-1) shouldBe 1
  }

  it should "be able to chain calls" in {
    val maybeNum = toJust(123)
    maybeNum.map(_.toString).getOrElse("-1") shouldBe "123"
  }

  it should "be able to be converted to a unary List[T]" in {
    val maybeStr = toJust("hello")
    maybeStr.toList match {
      case h :: Nil => h shouldBe "hello"
      case _ => fail(".toList on a Just[T] should have evaluated to a unary List[T]")
    }
  }
}
