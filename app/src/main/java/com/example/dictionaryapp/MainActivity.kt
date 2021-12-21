package com.example.dictionaryapp

import android.app.DownloadManager
import android.content.Intent
import android.location.GnssAntennaInfo
import android.media.MediaCodec
import android.net.nsd.NsdManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val findButton: Button = findViewById(R.id.find_button)
        searchEditText = findViewById(R.id.search_edit_text)



        findButton.setOnClickListener {
            stringRequest()
        }


    }

    private fun extractDefinitionFromJason(response : String){
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShotDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShotDefinition.get(0)

        val intent = Intent(this,DefinitionWordActivity::class.java)
        intent.putExtra(KEY,firstShortDefinition.toString())
        startActivity(intent)

    }

    private fun getUrl(): String {
        val word = searchEditText.text
        val apiKey = "a13b6fd3-80c2-44de-a1a4-d40b14184662"
        val url =
            "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"
        return url
    }

    private fun stringRequest(){

        val url = getUrl()
       val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url, {
                response ->

            try {
                extractDefinitionFromJason(response)
            } catch (exception : Exception){
                exception.printStackTrace()
            }

        }, {
                error -> Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
        })
        queue.add(stringRequest)

    }
}


