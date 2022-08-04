package example.boilerplate.file

import javax.persistence.*

@Entity
class File(
    @Id @GeneratedValue
    @Column(name = "file_id")
    val id: Long? = null,

    var originalFilename: String = "",
    var serverFilename: String = "",
)
