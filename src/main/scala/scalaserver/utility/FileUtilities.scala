package scalaserver.utility
import java.io.FileInputStream
import scala.io.BufferedSource

 object  FileUtilities {
   private def getSourceFromFile(path : String) : BufferedSource = {
     val inputStream = new FileInputStream(path);
     return scala.io.Source.fromInputStream(inputStream);
   }

  def readFile(path : String, length : Int): String = {
    val bufferedSource = getSourceFromFile(path);
    val characters = bufferedSource.take(length);
    var result:Array[Char] =  new Array[Char](length);
    characters.copyToArray(result);
    return new String(result).trim();
  }

   def readFile(path : String): String ={
     val bufferedSource = getSourceFromFile(path);
     val lines = bufferedSource.getLines();
     var result : StringBuilder = new StringBuilder();
     while (lines.hasNext){
       result.append(lines.next());
       result.append("\n");
     }
     return result.toString();
   }
}

