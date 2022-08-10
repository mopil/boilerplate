package example.boilerplate

import jdk.nashorn.internal.objects.NativeRegExp.test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class BoilerplateApplication

fun main(args: Array<String>) {
}


fun lambda(x: Int -> y) =

