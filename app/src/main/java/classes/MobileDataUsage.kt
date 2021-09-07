package classes

import java.nio.file.Path
import kotlin.io.path.fileSize

class MobileDataUsage {
    fun calculateMobileDataUsage (files: List<Path>) : Int{
        var sum: Long = 0
        for (path in files){
            sum += path.fileSize()
        }
        TODO("Figure out what files will be uploaded and from where and all that jazz")
    }
}