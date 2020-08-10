package com.example.kartellderliebe.rss.common

import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class HTTPDataHandler {

    var stream : String = ""

    fun getHTTPData(urlString : String) : String{
        try {
            val url = URL(urlString)
            val urlConnection : HttpURLConnection = url.openConnection() as HttpURLConnection
            urlConnection.apply {  }.connect()

            if(urlConnection.responseCode == HttpURLConnection.HTTP_OK){
                val input = BufferedInputStream(urlConnection.inputStream)
                val reader = BufferedReader(InputStreamReader(input))
                val sb : StringBuilder = StringBuilder("")
                stream = input.bufferedReader().use(BufferedReader::readText)
                urlConnection.disconnect()
            }

        }catch (e : MalformedURLException){
            e.printStackTrace()
        }catch (e : IOException){
            e.printStackTrace()
        }
        return stream
    }
}