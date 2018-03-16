package purepy

trait SupportedTypes
object SupportedTypes {
  final case object PythonUnit extends SupportedTypes
  final case class PythonString(v: String) extends SupportedTypes
  final case class PythonInt(v: Int) extends SupportedTypes
  final case class PythonLong(v: Long) extends SupportedTypes
  final case class PythonBoolean(v: Boolean) extends SupportedTypes
  final case class PythonList(v: SupportedTypes) extends SupportedTypes
}
