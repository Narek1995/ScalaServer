package scalaserver.api

import java.io.FileInputStream
import java.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestMethod, RequestParam, ResponseBody}
import scalaserver.utility.FileUtilities



@Controller
@RequestMapping(path = Array("/api"))
class ApiController() {

  @GetMapping(path = Array("/time"))
  @ResponseBody
  def  getEpochTime: Long = {
     System.currentTimeMillis();
  }

  @GetMapping(path = Array("/bytesOfFile"))
  @ResponseBody
  def getBytesOfFile(@RequestParam(name = "length") length:Int,
                    @Value("${path.to.file}") file : String) : String ={
    return FileUtilities.readFile(file, length);
  }

  @GetMapping(path = Array("/readFile"))
  @ResponseBody
  def readFile(@RequestParam(name = "path") path:String) : String ={
    return FileUtilities.readFile(path);
  }
}
