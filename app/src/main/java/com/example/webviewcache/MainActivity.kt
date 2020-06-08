package com.example.webviewcache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.webkit.WebSettings
import com.android.lib_js_cache.JsCacheWebviewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "http://www.baidu.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initWebView()
        webview.loadUrl(BASE_URL)
    }

    private fun initWebView() {
        webview.webViewClient = JsCacheWebviewClient(this, BASE_URL)
        val settings = webview.settings
        settings.allowFileAccess = true
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
    }
}
