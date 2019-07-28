package api

import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.mockito.Matchers
import org.mockito.Mockito.{doReturn, doThrow}
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, status}
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import scalaserver.api.FileApiController
import scalaserver.exceptions.{CustomExceptionHandler, RequestProcessingError}
import scalaserver.utility.FileUtilities

/**
 * Test Api-s from FileApiController
 */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes= Array(classOf[FileApiControllerTest],
  classOf[CustomExceptionHandler]),webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
class FileApiControllerTest {
  private var mockMvc:MockMvc  = null
  @MockBean
  private val fileUtilities: FileUtilities = null;
  private val testString : String = "Test string";
  private var requestProcessingError:RequestProcessingError = null;

  /**
   * Initial setup before test cases
   */
  @Before
  def setup{
    this.mockMvc = MockMvcBuilders.standaloneSetup(new FileApiController(fileUtilities)).
      setControllerAdvice(new CustomExceptionHandler()).build()
      requestProcessingError = new RequestProcessingError("Custom Error Text", HttpStatus.BAD_REQUEST);
  }

  /**
   * test case for /api/bytesOfFile
   * expects testString from api
   */
  @Test
  def readFileTest{
    doReturn(testString).when(fileUtilities).readFile(Matchers.anyString(), Matchers.anyInt());
    mockMvc.perform(get("/file/bytes?length=10")).andExpect(status.isOk).andExpect(
    content.string(testString));
  }

  /**
   * test case for /api/bytesOfFile with exception
   * expects status code and message from requestProcessingError field
   */
  @Test
  def readFileExceptionTest: Unit ={
    doThrow(requestProcessingError).when(fileUtilities).readFile(Matchers.anyString(), Matchers.anyInt());
    mockMvc.perform(get("/file/bytes?length=10")).andExpect(status.is(requestProcessingError.status.value())).andExpect(
      content.string(requestProcessingError.message));
  }

  /**
   * test case for /api/readFile
   * expects testString from api
   */
  @Test
  def readFullFileTest{
    doReturn(testString).when(fileUtilities).readFile(Matchers.anyString());
    mockMvc.perform(get("/file/content?path=testPath")).andExpect(status.isOk).andExpect(content.string(testString));
  }

  /**
   * test case for /api/readFile with exception
   * expects status code and message from requestProcessingError field
   */
  @Test
  def readFullFileExceptionTest{
    doThrow(requestProcessingError).when(fileUtilities).readFile(Matchers.anyString());
    mockMvc.perform(get("/file/content?path=testPath")).andExpect(status.is(requestProcessingError.status.value())).andExpect(
      content.string(requestProcessingError.message));
  }
}
