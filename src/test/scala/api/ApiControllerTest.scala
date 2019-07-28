package api

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers
import org.mockito.Mockito.{doReturn, doThrow}
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, status}
import scalaserver.api.ApiController
import scalaserver.exceptions.{CustomExceptionHandler, RequestProcessingError, ResponseText}
import scalaserver.utility.FileUtilities

@RunWith(classOf[SpringRunner])
@SpringBootTest(classes= Array(classOf[ApiController], classOf[CustomExceptionHandler]),webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
class ApiControllerTest {
  private var mockMvc:MockMvc  = null
  @MockBean
  private var fileUtilities: FileUtilities = null;
  private val testString : String = "Test string";

  import org.junit.Before
  import org.springframework.test.web.servlet.setup.MockMvcBuilders

  @Before
  def setup{
    this.mockMvc = MockMvcBuilders.standaloneSetup(new ApiController(fileUtilities)).
      setControllerAdvice(new CustomExceptionHandler()).build()
  }
  @Test
  def readFileTest{
    doReturn(testString).when(fileUtilities).readFile(Matchers.anyString(), Matchers.anyInt());
    mockMvc.perform(get("/api/bytesOfFile?length=10")).andExpect(status.isOk).andExpect(
    content.string(testString));
    doThrow(new RequestProcessingError(ResponseText.FILE_NOT_FOUND_ERROR_TEXT)).when(fileUtilities).readFile(Matchers.anyString(), Matchers.anyInt());
    mockMvc.perform(get("/api/bytesOfFile?length=10")).andExpect(status.is(400)).andExpect(
      content.string(ResponseText.FILE_NOT_FOUND_ERROR_TEXT));

  }

  @Test
  def readFullFileTest{
    doReturn(testString).when(fileUtilities).readFile(Matchers.anyString());
    mockMvc.perform(get("/api/readFile?path=testPath")).andExpect(status.isOk).andExpect(content.string(testString));
    doThrow(new RequestProcessingError(ResponseText.FILE_NOT_FOUND_ERROR_TEXT)).when(fileUtilities).readFile(Matchers.anyString());
    mockMvc.perform(get("/api/readFile?path=testPath")).andExpect(status.is(400)).andExpect(
      content.string(ResponseText.FILE_NOT_FOUND_ERROR_TEXT));
  }
}
