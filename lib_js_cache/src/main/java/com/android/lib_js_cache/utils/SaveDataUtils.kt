package com.android.lib_js_cache.utils


import android.util.Log
import android.webkit.WebResourceResponse
import okhttp3.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.net.URL

/**
 * js,css文件存储到本地
 */
object SaveDataUtils {

    /**
     * 得到WebResourceResponse对象
     * @return
     */
    fun getWebResource(uFile: File, url: String): WebResourceResponse? {
        try {
            val uri = URL(url)
            val connection = uri.openConnection()
            val contentType = connection.contentType
            val contentEncoding = connection.contentEncoding
            Log.e("SaveDataUtils",contentType + contentEncoding)
            val fileInputStream = FileInputStream(uFile)
            return WebResourceResponse(contentType, contentEncoding, fileInputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    /**
     * 把js,css保存在本地
     * @param uFile 本地存储的文件名File路径
     * @param url 将要下载的js,css文件
     */
    fun downLoadFile(uFile: File, url: String) = try {
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url(url)
                .build()
        okHttpClient.newCall(request).execute().body()?.let {
            it.byteStream().use { stream ->
                FileOutputStream(uFile).use { outStream ->
                    IOUtils.copy(stream, outStream)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
