package example.boilerplate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class BoilerplateApplication

fun main(args: Array<String>) {
    runApplication<BoilerplateApplication>(*args)
}
