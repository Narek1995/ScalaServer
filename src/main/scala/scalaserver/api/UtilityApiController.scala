package scalaserver.api

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, ResponseBody}
import scalaserver.exceptions.RequestProcessingError
import scalaserver.utility.FileUtilities

/**
 * Api controller to handle incoming /utility API requests.
 *
 */
@Controller
@RequestMapping(path = Array("/utility"))
class UtilityApiController() {



  /**
   * Function to handle /time API
   * @return Long value of current time in milliseconds
   */
  @GetMapping(path = Array("/time"))
  @ResponseBody
  def  getEpochTime: Long = {
     System.currentTimeMillis();
  }


}
