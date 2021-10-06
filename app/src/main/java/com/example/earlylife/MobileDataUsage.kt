/**
 * Skeleton class created in the case that mobile data would be used in the app so that
 * the user could be warned of how much data would be used.
 * This class was not used since the app does not use mobile data.
 */

package com.example.earlylife

import java.nio.file.Path
import kotlin.io.path.fileSize

class MobileDataUsage {
    fun calculateMobileDataUsage (files: List<Path>) : Long{
        var sum: Long = 0
        for (path in files){
            sum += path.fileSize()
        }

        return sum
        TODO("Figure out what files will be uploaded and from where")
    }

    fun showWarning() {


    }
}