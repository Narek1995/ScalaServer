package scalaserver.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, ResponseBody}
import scalaserver.utility.Utilities

/**
 * Api controller to handle incoming /utility API requests.
 *
 */
@Controller
@RequestMapping(path = Array("/utility"))
class UtilityApiController(@Autowired utils:Utilities) {
    private val utilities : Utilities = utils;


  /**
   * Function to handle /time API
   * @return Long value of current time in milliseconds
   */
  @GetMapping(path = Array("/time"))
  @ResponseBody
  def  getEpochTime: Long = {
     utils.getCurrentTimeInMilliseconds;
  }


}
