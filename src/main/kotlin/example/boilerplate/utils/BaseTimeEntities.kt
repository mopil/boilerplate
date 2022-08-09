package example.boilerplate.utils

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

/**
 * 시간 포멧을 변경하지 않아도 될 경우 (어노테이션으로 간략하게 사용 가능)
 * 이러면 ISO 표준 포맷으로 저장 된다.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity1 {
    @CreatedDate
    lateinit var createTime: LocalDateTime
    @LastModifiedDate
    lateinit var updateTime: LocalDateTime
}

/**
 * 시간 포멧을 변경해서 사용하고 싶은 경우
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity2 {

    /**
     * lateinit var 로 선언하면 Uninitialized 코틀린 에러가 발생한다.
     * 따라서 String 으로 바꾸고 초기값을 지정해 줘야한다.
     */
    var createTime: String = ""
    var updateTime: String = ""

    @PrePersist
    fun prePersist() {
        this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

    @PreUpdate
    fun preUpdate() {
        this.updateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}