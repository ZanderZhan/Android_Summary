package com.example.scopedstorage.toolkit

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi

/**
 * MediaQuery：
 * Created by zander on  2021/02/27
 */

class MediaQuery private constructor(val context: Context) {

    companion object {
        fun with(context: Context): MediaQuery {
            return MediaQuery(context)
        }
    }

    fun queryAllImages(): List<Uri> {

        val result = mutableListOf<Uri>()

        val query = context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                "${MediaStore.Images.Media.DATE_ADDED} ASC")

        query?.let { cursor ->
            while (cursor.moveToNext()) {
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                    val authorColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.OWNER_PACKAGE_NAME)
//                    val author = cursor.getString(authorColumn)
//                    Log.i("zander", "produced by $author")
//                }

                val id = cursor.getLong(idColumn)
                val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                result.add(uri)
            }

        }
        query?.close()

        return result
    }

    fun queryImagesByName(name: String): List<Uri> {

        val result = mutableListOf<Uri>()

        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME
        )

        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(name)

        val sortOrder = "${MediaStore.Images.Media.DISPLAY_NAME} ASC"

        val query = context.contentResolver.query(collection, projection, selection, selectionArgs, sortOrder)

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)

                val uri = ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                )
                result.add(uri)
            }
        }
        query?.close()

        return result
    }

}