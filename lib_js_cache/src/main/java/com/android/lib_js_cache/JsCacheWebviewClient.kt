package com.android.lib_js_cache

import android.content.Context
import android.net.http.SslError
import android.util.Log
import android.webkit.*
import com.android.lib_js_cache.utils.MD5Util
import com.android.lib_js_cache.utils.SaveDataUtils
import java.io.File

class JsCacheWebviewClient(val context: Context,val baseUrl:String): WebViewClient() {
    val TAG = "JsCacheWebviewClient"
    override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
        val url = request?.url
        val method = request?.method
        val jsCacheDir = File(context.cacheDir, "jsCache")
        if (!jsCacheDir.exists()){
            jsCacheDir.mkdirs()
        }

        //url以baseUrl开头，并且请求方式为GET 走缓存逻辑
        if (url.toString().startsWith(baseUrl) && method?.equals("GET") == true && url.toString() != baseUrl){
            val tempFile = File(jsCacheDir, MD5Util.encode(url.toString()))
            if (tempFile.exists()){
                Log.d(TAG,"读取缓存>>>>" + url.toString())
                return SaveDataUtils.getWebResource(tempFile,url.toString())
            }else {
                Log.d(TAG,"缓存文件>>>>" +url.toString() )
                SaveDataUtils.downLoadFile(tempFile,url.toString())
            }
        }
        return super.shouldInterceptRequest(view, request)
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        handler?.proceed()
    }
}