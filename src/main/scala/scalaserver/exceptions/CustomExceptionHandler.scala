package scalaserver.exceptions

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.{ControllerAdvice, ExceptionHandler, ResponseBody}


/**
 * Custom exception handler to handle custom errors thrown during API handling
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class CustomExceptionHandler {


  /**
   * Method to handle RequestProcessingError -s thrown during API handling
   * @param ex
   * @return
   */
  @ExceptionHandler(Array(classOf[RequestProcessingError]))
  @ResponseBody protected def handleRequestProcessingException(ex: RequestProcessingError): ResponseEntity[Any] = {
    new ResponseEntity[Any](ex.message, ex.status)
  }

}
