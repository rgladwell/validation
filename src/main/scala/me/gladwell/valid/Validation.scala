package me.gladwell.valid

sealed trait Validation[+T]

final case class Valid[T](value: T) extends Validation[T]

final case class Invalid[E](errors: E*) extends Validation[Nothing]

object Validation {

  def apply[T](suspect: T)(implicit validate: Validator[T]): Validation[T] =
    validate(suspect)

}
