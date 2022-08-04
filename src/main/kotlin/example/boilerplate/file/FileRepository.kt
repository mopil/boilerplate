package example.boilerplate.file

import example.boilerplate.file.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, Long> {
    fun findByOriginalFilename(originalFilename: String) : File?
    fun findByServerFilename(serverFilename: String) : File?
}
