package scalaserver.api

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, ResponseBody}
import scalaserver.exceptions.RequestProcessingError
import scalaserver.utility.FileUtilities

/**
 * Api controller to handle incoming /file API requests.
 *
 */
@Controller
@RequestMapping(path = Array("/file"))
class FileApiController(@Autowired fileUtils : FileUtilities) {
  private var fileUtilities : FileUtilities = fileUtils;
  /**
   * Method to handle /bytesOfFile api, which returns bytes from file stored locally
   * @param length length of string to retrieve from the file
   * @param file path to the file stored locally, value is retried from application.properties file: Before byild
   *             setup path value at application.properties
   * @throws scalaserver.exceptions.RequestProcessingError
   * @return String value retrieved from file.
   */
  @throws(classOf[RequestProcessingError])
  @GetMapping(path = Array("/bytes"))
  @ResponseBody
  def getBytesOfFile(@RequestParam(name = "length") length:Int,
                     @Value("${path.to.file}") file : String) : String ={
    return fileUtilities.readFile(file, length);
  }

  /**
   * Method to handle /readFile api, which returns content of the file with given path
   * @param path to the file in server
   * @throws scalaserver.exceptions.RequestProcessingError
   * @return String content of a file located with given path
   */
  @throws(classOf[RequestProcessingError])
  @GetMapping(path = Array("/content"))
  @ResponseBody
  def readFile(@RequestParam(name = "path") path:String) : String ={
    return fileUtilities.readFile(path);
  }

}
