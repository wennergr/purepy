package purepy

import cats.syntax.functor._
import cats.effect.Sync
import cats.{Monad, ~>}
import jep.Jep
import purepy.python.PythonOp
import purepy.python.PythonOp._

class JepInterpreter[F[_]: Monad](jep: => Jep) extends Interpreter[F] {
  private val assignment = "__last_evaluated_value"

  def asA[A](a: Any) = a.asInstanceOf[A]

  def trans(implicit S: Sync[F]) = new (PythonOp ~> F) {
    override def apply[A](fa: PythonOp[A]): F[A] = fa match {
      case LoadScript(scriptName) =>
        S.delay(jep.runScript(scriptName)).map(asA[A])

      case Invoke(functionName: String, arguments: List[Any]) =>
        S.delay(jep.invoke(functionName, arguments.map(asA[Object]): _*)).map(asA[A])

      case Eval(code: String) =>
        S.delay(jep.eval(code)).map(asA[A])

      case Expression(code: String) => {
        val codeWithAssignment = assignment + " = " + code

        S.delay {
          // jep.eval returns false if the provided python code doesn't compile.
          // TODO: Don't throw exceptions here
          // TODO: Add more details around how the "assignment" could cause issues
          if (jep.eval(codeWithAssignment)) {
            throw new IllegalStateException("Provided code does not compile: \n" + codeWithAssignment)
          }

          jep.getValue(assignment)
        }.map(asA[A])
      }
    }
  }
}
