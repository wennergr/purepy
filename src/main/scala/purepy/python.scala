package purepy

import cats.free._

object python {

  type PythonIO[A] = Free[PythonOp, A]

  /**
    * Algebra for supported python operations
    */
  trait PythonOp[A]

  object PythonOp {
   final case class LoadScript(name: String) extends PythonOp[Unit]
   final case class Invoke[A](functionName: String, arguments: List[Any]) extends PythonOp[A]
   final case class Eval(code: String) extends PythonOp[Boolean]
   final case class Expression[A](code: String) extends PythonOp[A]
  }

  import PythonOp._

   /**
     * Loads a python script into the interperator
     *
     * @param name Path to script to be loaded (root is the classpath)
     */
  def loadScript(name: String): PythonIO[Unit] = Free.liftF(LoadScript(name))

  /**
    * Invoke a python function and return it's value
    *
    * @param functionName Name of function to call
    * @param arguments List of arguments for the function
    * @tparam A Return type of the called python function
    */
  def invoke[A](functionName: String, arguments: List[Any]): PythonIO[A] =
      Free.liftF(Invoke(functionName, arguments))

  /**
    * Evaluate python code
    *
    * @param code Python code to evaluate
    * @return Boolean true if the python code is sound
    */
  def eval(code: String): PythonIO[Boolean] = Free.liftF(Eval(code))

  /**
    * Execute an expression and return the data from it
    *
    * @param code Python code to evaluate
    * @tparam A Return type of python expression
    */
  def expression[A](code: String): PythonIO[A] = Free.liftF(Expression(code))

}

