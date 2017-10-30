package me.gladwell.valid

import utest._
import cats.syntax.flatMap._
import cats.syntax.functor._
import cats.syntax.eq._
import org.scalacheck.Arbitrary

object ValidationSpec extends TestSuite {

  val tests = Tests {
    "Validation should" - {

      implicit val testValidation = Validator[Int]{ _ == 1 }

      "create valid value" - {
        assert(Validation(1).isInstanceOf[Valid[_]])
      }

      "create invalid value" - {
        assert(Validation(2).isInstanceOf[Invalid[_]])
      }

      "compose valid validations" - {
        val val1: Validation[Int] = Valid(1)
        val val2: Validation[Int] = Valid(2)

        val composedValid =
          for {
            v1 <- val1
            v2 <- val2
          } yield v1 + v2

        assert(composedValid == Valid(3))
      }

      "compose valid and invalid validations" - {
        val val1: Validation[Int] = Valid(1)
        val val2: Validation[Int] = Invalid("error")

        val composedValid =
          for {
            v1 <- val1
            v2 <- val2
          } yield v1 + v2

        assert(composedValid == Invalid("error"))
      }

      "be equal for equal validated values" - {
        val val1: Validation[Int] = Valid(1)
        val val2: Validation[Int] = Valid(1)

        assert(val1 === val2)
      }

      "be unequal for unequal validated values" - {
        val val1: Validation[Int] = Valid(1)
        val val2: Validation[Int] = Valid(2)

        assert(val1 =!= val2)
      }

      "be equal for equal invalid values" - {
        val invalid1: Validation[Int] = Invalid(1, 2)
        val invalid2: Validation[Int] = Invalid(1, 2)

        assert(invalid1 === invalid2)
      }

      "be unequal for unequal invalid values" - {
        val invalid1: Validation[Int] = Invalid(1, 2)
        val invalid2: Validation[Int] = Invalid(2, 2)

        assert(invalid1 =!= invalid2)
      }

      "satisfy monadic laws" - {
        import cats.implicits._
        import cats.laws.discipline.MonadTests

        implicit def arbValidation[T](implicit a: Arbitrary[T]): Arbitrary[Validation[T]] =
          Arbitrary {
            for(e <- Arbitrary.arbitrary[T]) yield Valid(e)
          }

        MonadTests[Validation].monad[Int, Int, Int].all.check()
      }
    }
  }

}
