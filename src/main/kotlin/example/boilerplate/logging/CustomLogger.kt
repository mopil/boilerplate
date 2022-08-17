package example.boilerplate.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * 코틀린에서는 롬복을 사용할 수 없어서, @Slf4j를 사용할 수 없다.
 * 따라서 커스텀 로거를 만들어서 사용해야 한다.
 */
inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}