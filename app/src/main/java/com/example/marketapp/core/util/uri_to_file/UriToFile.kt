package com.example.marketapp.core.util.uri_to_file

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

fun Uri.ToFile(context: Context,): File? {


    val contentResolver: ContentResolver = context.contentResolver
    val tempFile = File(context.cacheDir, "temp_file")

    try {
        val inputStream: InputStream? = contentResolver.openInputStream(this)
        inputStream?.let {
            val outputStream = FileOutputStream(tempFile)
            val buffer = ByteArray(4 * 1024) // 4K buffer size
            var read: Int

            while (it.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()
        }

        return tempFile
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}
