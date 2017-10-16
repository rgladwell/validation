package me.gladwell.valid

trait Validator[T] extends Function1[T, Validation[T]]

object Validator {

  def apply[T](p: T => Boolean): Validator[T] = new Validator[T] {

    override def apply(value: T): Validation[T] = {
      if(p(value)) Valid(value)
      else Invalid("invalid")
    }

  }

}
