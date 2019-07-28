package scalaserver.utility

import org.springframework.context.annotation.Configuration

@Configuration
class Utilities {

  def getCurrentTimeInMilliseconds : Long = {
      System.currentTimeMillis();
  }
}
