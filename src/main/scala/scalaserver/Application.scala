package scalaserver

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}

@SpringBootApplication
@EnableAutoConfiguration(exclude =Array(classOf[DataSourceAutoConfiguration], classOf[HibernateJpaAutoConfiguration]))
class Application

object Application extends App {
  SpringApplication.run(classOf[Application]);
}