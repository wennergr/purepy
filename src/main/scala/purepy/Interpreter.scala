package purepy

import cats.effect.Sync
import cats.~>
import purepy.python.PythonOp

/**
  * Interpreter from Python Algebra to F[_]
  */
trait Interpreter[F[_]] {
  def trans(implicit S: Sync[F]): PythonOp ~> F
}