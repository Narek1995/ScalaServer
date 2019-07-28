package scalaserver.exceptions

import org.springframework.http.HttpStatus

/**
 * Custom exception to describe errors generated during api handling
 *
 * @param ms String message to describe error
 * @param st Integer to describe HTTP error
 */
class RequestProcessingError(ms : String, st:HttpStatus) extends Exception{
    val message : String = ms;
    val status : HttpStatus = st;
}
