package scalaserver.utility
import java.io.{FileInputStream, FileNotFoundException}

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import scalaserver.exceptions.{RequestProcessingError, ResponseText}

import scala.io.BufferedSource

/**
 * Utility Class containing functions to work with file system
 */
@Configuration
 class  FileUtilities {
  /**
   * Method to return BufferedSource form file with given path
   * @param path
   * @throws scalaserver.exceptions.RequestProcessingError
   * @return
   */
  @throws(classOf[RequestProcessingError])
  def getSourceFromFile(path : String) : BufferedSource = {
    try {
      val inputStream = new FileInputStream(path);
      return scala.io.Source.fromInputStream(inputStream);
    }catch {
      case e : FileNotFoundException =>
      throw new RequestProcessingError(ResponseText.FILE_NOT_FOUND_ERROR_TEXT, HttpStatus.BAD_REQUEST);
    }
   }

  /**
   * Method to return first N bytes from file with given path
   * @param path to file at file system
   * @param length of first N bytes to retrieve
   * @throws scalaserver.exceptions.RequestProcessingError
   * @return
   */
  @throws(classOf[RequestProcessingError])
  def readFile(path : String, length : Int): String = {
    val bufferedSource = getSourceFromFile(path);
    val characters = bufferedSource.take(length);
    var result : StringBuilder = new StringBuilder();
    while (characters.hasNext){
      result.append(characters.next());
    }
    return result.toString();
  }

  /**
   * Method to retrieve content of file with given path
   * @param path
   * @throws scalaserver.exceptions.RequestProcessingError
   * @return
   */
  @throws(classOf[RequestProcessingError])
   def readFile(path : String): String ={
     val bufferedSource = getSourceFromFile(path);
     var result : StringBuilder = new StringBuilder();
     var reader = bufferedSource.reader();
     var character = reader.read();
     while (character != -1){
       result.append(character.toChar);
       character = reader.read();
     }
     return result.toString();
   }
}

