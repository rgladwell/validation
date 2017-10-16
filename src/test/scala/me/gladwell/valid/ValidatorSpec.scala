package me.gladwell.valid

import utest._

object ValidatorSpec extends TestSuite {

  val tests = Tests {
    "Validator should" - {

      implicit val predicateValidation = Validator[Int]{ _ == 1 }

      "validate predicate" - {
        assert(predicateValidation(1).isInstanceOf[Valid[_]])
      }

      "copy valid value for valid predicate" - {
        assert(predicateValidation(1) == Valid(1))
      }

      "invalidate predicate" - {
        assert(predicateValidation(2).isInstanceOf[Invalid[_]])
      }

      "return default error message for invalid predicate" - {
        assert(predicateValidation(2) == Invalid("invalid"))
      }

    }
  }

}
