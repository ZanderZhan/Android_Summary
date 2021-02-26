package com.example.scopedstorage

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scroped_storage)

        // 1. 拍照并获取返回的图片，将其存到媒体文件中，我们自动拥有这个文件的操作权，此过程，不需要申请任何权限。
        val name = "take_pic_preview" + System.currentTimeMillis()
        var picUri : Uri? = null
        val picLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            if (it != null) {
                picUri = insertToGallery(name, it)
                if (picUri != null) {
                    Toast.makeText(baseContext, "存储成功", Toast.LENGTH_SHORT).show()
                    // 读取自己刚存进去的文件
                    val bitmap = readBitmap(picUri!!)
                    if (bitmap != null) {
                        findViewById<AppCompatImageView>(R.id.image_view).setImageBitmap(bitmap)
                    }

                } else {
                    Toast.makeText(baseContext, "存储失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
        findViewById<Button>(R.id.button_1).setOnClickListener {
            // 调用拍照功能，接受 bitmap 返回用到了 Activity Result Api
            picLauncher.launch(null)
        }

        // 2. 删除我们刚刚保存下来的图片，不需要申请额外的权限
        findViewById<Button>(R.id.button_2).setOnClickListener {
            // 1. 存图片时将 uri 保存下来
//            val uri = picUri
            // 2. 通过 name 去查询 uri
            val uri = getContentUriByName(name)
            if (uri != null) {
                val result = baseContext.contentResolver.delete(uri, null, null)
                if (result > 0) {
                    Toast.makeText(baseContext, "删除成功", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "删除失败", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun insertToGallery(name: String, bitmap: Bitmap): Uri? {

        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "image")
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name)
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg}")
        val contentUri = baseContext.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        )

        if (contentUri != null) {
            baseContext.contentResolver.openFileDescriptor(contentUri, "w", null)
                .use { fileDescriptor ->
                    fileDescriptor?.let {
                        val out = FileOutputStream(it.fileDescriptor)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                        out.close()
                        it.close()
                        return contentUri
                    }
                }

        }

        return null
    }

    fun readBitmap(uri: Uri): Bitmap? {
        val fd = baseContext.contentResolver.openFileDescriptor(uri, "r")
        if (fd != null) {
            val bitmap = BitmapFactory.decodeFileDescriptor(fd.fileDescriptor)
            fd.close()
            return bitmap
        }
        return null
    }

    fun getContentUriByName(name: String): Uri? {

        var result: Uri? = null

        val collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
        } else MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.SIZE
        )

        val selection = "${MediaStore.Images.Media.DISPLAY_NAME} = ?"
        val selectionArgs = arrayOf(name)

        val sortOrder = "${MediaStore.Images.Media.DISPLAY_NAME} ASC"

        val query = baseContext.contentResolver.query(collection, projection, selection, selectionArgs, sortOrder)

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
            val sizeColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val size = cursor.getString(sizeColumn)

                result = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )

                return@use
            }

        }

        return result
    }
}