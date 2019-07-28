package utility

import java.io.ByteArrayInputStream
import java.nio.charset.Charset

import org.junit.Assert.assertEquals
import org.junit.{Before, Test}
import org.mockito.Mockito._
import scalaserver.utility.FileUtilities

import scala.io.BufferedSource

/**
 * Unit tests to test FileUtilities class
 */
class FileUtilitiesTest {
    var testString : String = "This is test string\n";
    var testLength = 5;
    var fileUtilities : FileUtilities = null;

  /**
   * function to generate BufferedSource from given string
   * @return
   */
    def getInputSource():BufferedSource = {
      val inputStream: ByteArrayInputStream = new ByteArrayInputStream(testString.getBytes(Charset.forName("UTF-8")))
      new BufferedSource(inputStream);
    }

  /**
   * Initial setup before test cases
   */
    @Before
    def setup {
      fileUtilities = spy(new FileUtilities());
      doReturn(getInputSource()).when(fileUtilities).getSourceFromFile("testPath"); //mock getSourceFromFile to return value from getInputSource()
    }

  /**
   * Unit test for readFile(String, Int) method
   * readFile method must return substring of testString with length of testLength
   */
  @Test
    def readFileTest {
      assertEquals(testString.substring(0, testLength), fileUtilities.readFile("testPath",testLength));
    }

  /**
   * Unit test for readFile(String) method
   * readFile method must return testString
   */
    @Test
  def readFullFileTests: Unit ={
      assertEquals(testString, fileUtilities.readFile("testPath"));
    }
}
