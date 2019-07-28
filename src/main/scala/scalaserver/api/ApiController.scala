package scalaserver.api

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, ResponseBody}
import scalaserver.exceptions.RequestProcessingError
import scalaserver.utility.FileUtilities

/**
 * Main Api controller to handle incoming API requests.
 *
 * @param fileUtils
 */
@Controller
@RequestMapping(path = Array("/api"))
class ApiController(@Autowired fileUtils : FileUtilities) {

  private var fileUtilities : FileUtilities = fileUtils;

  /**
   * Function to handle /time API
   * @return Long value of current time in milliseconds
   */
  @GetMapping(path = Array("/time"))
  @ResponseBody
  def  getEpochTime: Long = {
     System.currentTimeMillis();
  }

  /**
   * Method to handle /bytesOfFile api, which returns bytes from file stored locally
   * @param length length of string to retrieve from the file
   * @param file path to the file stored locally, value is retried from application.properties file
   * @throws scalaserver.exceptions.RequestProcessingError
   * @return String value retrieved from file.
   */
  @throws(classOf[RequestProcessingError])
  @GetMapping(path = Array("/bytesOfFile"))
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
  @GetMapping(path = Array("/readFile"))
  @ResponseBody
  def readFile(@RequestParam(name = "path") path:String) : String ={
    return fileUtilities.readFile(path);
  }
}
