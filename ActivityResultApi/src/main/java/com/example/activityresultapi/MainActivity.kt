package com.example.activityresultapi

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class MainActivity: AppCompatActivity() {

    private val myActivityResult = object: ActivityResultContract<String, String>() {
        override fun createIntent(context: Context, input: String?): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra("param", input ?: "")
            }
        }

        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if (resultCode == RESULT_OK) {
                return intent?.getStringExtra("result") ?: ""
            }
            return ""
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            Request_Code_1 -> {
                if (resultCode == RESULT_OK) {
                    val result = data?.getStringExtra("result") ?: "empty"
                    findViewById<TextView>(R.id.result).text = "get content from second activity: $result"
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_main)

        // 1. 传统方式 startActivityForResult
        findViewById<Button>(R.id.button).setOnClickListener {
            startActivityForResult(Intent(baseContext, SecondActivity::class.java).apply {
                putExtra("param", "from start activity for result")
            }, Request_Code_1)
        }

        // 2. result api，如果你在 onClick 里面 注册，将会得到一个 Exception
//        findViewById<Button>(R.id.button1).setOnClickListener {
//            val myActivityLauncher = registerForActivityResult(myActivityResult) { result ->
//                findViewById<TextView>(R.id.result).text = "get content from second activity: $result"
//            }
//            myActivityLauncher.launch("from result api")
//        }

        // 2. result api
        val myActivityLauncher = registerForActivityResult(myActivityResult) { result ->
            findViewById<TextView>(R.id.result).text = "get content from second activity: $result"
        }
        findViewById<Button>(R.id.button1).setOnClickListener {
            myActivityLauncher.launch("from result api")
        }

        // 3. 预定义 contract - StartActivityForResult
        val startActivityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                findViewById<TextView>(R.id.result).text = "get content from second activity: ${it.data?.getStringExtra("result")}"
            }
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivityLauncher.launch(Intent(baseContext, SecondActivity::class.java).apply {
                putExtra("param", "from ActivityResultContracts - StartActivityForResult")
            })
        }

        // 4. 预定义 contract - Start`IntentSe`nderForResult
        val startActivitySenderForResultLauncher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) {
            if (it.resultCode == RESULT_OK) {
                findViewById<TextView>(R.id.result).text = "get content from second activity: ${it.data?.getStringExtra("result")}"
            }
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            // todo need a pending intent
//            startActivitySenderForResultLauncher.launch()
        }

        // 5. 预定义 contract - RequestMultiplePermissions，RequestPermission
        val requestMultiPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            findViewById<TextView>(R.id.result).text = "request permissions result : ${it.all { it.value }}"
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            requestMultiPermissionLauncher.launch(arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ))
        }

        // 6. 预定义 contract - TakePicturePreview
        val takePicturePreviewLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            findViewById<AppCompatImageView>(R.id.image_view).setImageBitmap(it)
        }
        findViewById<Button>(R.id.button5).setOnClickListener {
            takePicturePreviewLauncher.launch(null)
        }

        // 7. 预定义 contract - TakePicture
        val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) {
//            findViewById<AppCompatImageView>(R.id.image_view).setImageBitmap(it)
        }
        findViewById<Button>(R.id.button6).setOnClickListener {
            // todo need a uri
//            takePictureLauncher.launch()
        }

        // 8. 预定义 contract - TakeVideo
        val takeVideoLauncher = registerForActivityResult(ActivityResultContracts.TakeVideo()) {
            findViewById<AppCompatImageView>(R.id.image_view).setImageBitmap(it)
        }
        findViewById<Button>(R.id.button7).setOnClickListener {
            // todo need a uri
            takeVideoLauncher.launch(null)
        }

        // 9. 预定义 contract - PickContact
        val pickContactLauncher = registerForActivityResult(ActivityResultContracts.PickContact()) {
            findViewById<TextView>(R.id.result).text = "get contact result: $it"
        }
        findViewById<Button>(R.id.button8).setOnClickListener {
            pickContactLauncher.launch(null)
        }

        // 10. 预定义 contract - GetContent、getMultiContent
        val getContentLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
            findViewById<TextView>(R.id.result).text = "get content uri: $it"
        }
        findViewById<Button>(R.id.button9).setOnClickListener {
            getContentLauncher.launch("image/*")
        }

        // 11. 预定义 contract - OpenDocument、OpenMultiDocument
        val openDocumentLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) {
            findViewById<TextView>(R.id.result).text = "open document result: $it"
        }
        findViewById<Button>(R.id.button10).setOnClickListener {
            openDocumentLauncher.launch(arrayOf("image/*"))
        }

        // 12. 预定义 contract - OpenDocumentTree
        val openDocumentTreeLauncher = registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) {
            findViewById<TextView>(R.id.result).text = "open document tree result: $it"
        }
        findViewById<Button>(R.id.button11).setOnClickListener {
            // todo need a uri
//            openDocumentTreeLauncher.launch()
        }

        // 13. 预定义 contract - OpCreateDocument
        val createDocumentLauncher = registerForActivityResult(ActivityResultContracts.CreateDocument()) {
            findViewById<TextView>(R.id.result).text = "create document result: $it"
        }
        findViewById<Button>(R.id.button12).setOnClickListener {
            createDocumentLauncher.launch("newFile.txt")
        }


    }

    companion object {

        const val Request_Code_1 = 1

    }

}