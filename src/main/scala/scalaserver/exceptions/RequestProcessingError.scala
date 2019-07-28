package scalaserver.exceptions

class RequestProcessingError(ms : String) extends Exception{
    val message : String = ms;
}
