package me.gladwell.valid

import cats.Monad
import cats.kernel.Eq

import scala.annotation.tailrec

sealed trait Validation[+T]

final case class Valid[T](value: T) extends Validation[T]

final case class Invalid[E](errors: E*) extends Validation[Nothing]

object Validation {

  def apply[T](suspect: T)(implicit validate: Validator[T]): Validation[T] =
    validate(suspect)

  implicit object ValidationMonad extends Monad[Validation] {

    override def pure[A](x: A): Validation[A] = Valid(x)

    override def flatMap[A, B](fa: Validation[A])(f: (A) => Validation[B]): Validation[B] =
      fa match {
        case Valid(v)   => f(v)
        case Invalid(e) => Invalid(e)
      }

    override def tailRecM[A, B](a: A)(f: (A) => Validation[Either[A, B]]): Validation[B] =  f(a) match {
      case Invalid(e)         => Invalid(e)
      case Valid(Left(nextA)) => tailRecM(nextA)(f)
      case Valid(Right(b))    => Valid(b)
    }

  }

  implicit def validationEquality[T] = new Eq[Validation[T]]() {

    override def eqv(x: Validation[T], y: Validation[T]): Boolean =
      (x, y) match {
        case (Valid(v1),      Valid(v2))      => v1 == v2
        case (Invalid(errs1), Invalid(errs2)) => println(s"$errs1 == $errs2 = ${errs1 == errs2}"); errs1 == errs2
        case _                                => println("mismatch"); false
      }

  }
}
