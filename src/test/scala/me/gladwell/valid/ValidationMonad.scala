package me.gladwell.valid

import cats.Monad

class ValidationMonad extends Monad[Validation] {

  override def pure[A](x: A) = Valid(x)

  override def flatMap[A, B](fa: Validation[A])(f: (A) => Validation[B]) = ???

  override def tailRecM[A, B](a: A)(f: (A) => Validation[Either[A, B]]): Validation[B] = ???

}
