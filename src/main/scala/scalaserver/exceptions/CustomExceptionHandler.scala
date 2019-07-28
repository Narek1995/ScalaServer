package scalaserver.exceptions

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler, ResponseBody}


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class CustomExceptionHandler {


  @ExceptionHandler(Array(classOf[RequestProcessingError]))
  @ResponseBody protected def handleRequestProcessingException(ex: RequestProcessingError): ResponseEntity[Any] = {
    new ResponseEntity[Any](ex.message, HttpStatus.BAD_REQUEST)
  }

}
