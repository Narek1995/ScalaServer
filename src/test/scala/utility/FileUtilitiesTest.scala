package utility

import java.io.ByteArrayInputStream
import java.nio.charset.Charset

import org.junit.Assert.assertEquals
import org.junit.{Before, Test}
import org.mockito.Mockito._
import scalaserver.utility.FileUtilities

import scala.io.BufferedSource

class FileUtilitiesTest {
    var testString : String = "This is test string\n";
    var testLength = 5;
    var fileUtilities : FileUtilities = null;

    def getInputSource():BufferedSource = {
      var inputStream : ByteArrayInputStream = new ByteArrayInputStream(testString.getBytes(Charset.forName("UTF-8")))
      return new BufferedSource(inputStream);
    }

    @Before
    def setup {

      fileUtilities = spy(new FileUtilities());
      doReturn(getInputSource()).when(fileUtilities).getSourceFromFile("testPath");
    }
    @Test
    def readFileTest {
      assertEquals(testString.substring(0, testLength), fileUtilities.readFile("testPath",testLength));
    }

    @Test
  def readFullFileTests: Unit ={
      assertEquals(testString, fileUtilities.readFile("testPath"));
    }
}
