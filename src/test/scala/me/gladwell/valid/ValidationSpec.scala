package me.gladwell.valid

import utest._
import Validation._

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

    }
  }

}
