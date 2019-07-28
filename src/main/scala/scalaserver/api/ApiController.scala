package scalaserver.api

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, ResponseBody}
import scalaserver.exceptions.RequestProcessingError
import scalaserver.utility.FileUtilities



@Controller
@RequestMapping(path = Array("/api"))
class ApiController(@Autowired fileUtils : FileUtilities) {

  private var fileUtilities : FileUtilities = fileUtils;

  @GetMapping(path = Array("/time"))
  @ResponseBody
  def  getEpochTime: Long = {
     System.currentTimeMillis();
  }

  @throws(classOf[RequestProcessingError])
  @GetMapping(path = Array("/bytesOfFile"))
  @ResponseBody
  def getBytesOfFile(@RequestParam(name = "length") length:Int,
                    @Value("${path.to.file}") file : String) : String ={
    return fileUtilities.readFile(file, length);
  }

  @throws(classOf[RequestProcessingError])
  @GetMapping(path = Array("/readFile"))
  @ResponseBody
  def readFile(@RequestParam(name = "path") path:String) : String ={
    return fileUtilities.readFile(path);
  }
}
