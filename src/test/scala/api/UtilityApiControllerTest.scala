package api

import org.junit.runner.RunWith
import org.junit.{Before, Test}
import org.mockito.Mockito.doReturn
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.{content, status}
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import scalaserver.api.UtilityApiController
import scalaserver.utility.Utilities

/**
 * Test Api-s from UtilityApiController
 */
@RunWith(classOf[SpringRunner])
@SpringBootTest(classes= Array(classOf[UtilityApiController]),webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
class UtilityApiControllerTest {
  @AutoConfigureMockMvc
  private var mockMvc:MockMvc  = null
  @MockBean
  private val utilities : Utilities = null;
  private val testValue : Long = 1000;

  /**
   * Initial setup before test cases
   */
  @Before
  def setup{
    this.mockMvc = MockMvcBuilders.standaloneSetup(new UtilityApiController(utilities)).build()
  }

  /**
   * test case for /utility/time
   * expects testString from api
   */
  @Test
  def getTimeTest{
    doReturn(testValue).when(utilities).getCurrentTimeInMilliseconds;
    mockMvc.perform(get("/utility/time")).andExpect(status.isOk).andExpect(
      content.string(testValue.toString));
  }
}
