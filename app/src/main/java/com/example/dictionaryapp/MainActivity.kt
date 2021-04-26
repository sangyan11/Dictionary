package com.example.dictionaryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val queue = Volley.newRequestQueue(this)
        var find_btn = findViewById<Button>(R.id.find)


        find_btn.setOnClickListener{

            val url = getUrl()

            val  stringRequest = StringRequest(Request.Method.GET,url,
                    {response ->

                        try {
                            extractdefinationfromJason(response)
                        }catch (exception : Exception){
                            exception.printStackTrace()
                        }




                    },

                    {error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()



                    }

            )
            queue.add(stringRequest)

        }
    }
    private fun extractdefinationfromJason(response:String){

        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getShortDefinition = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getShortDefinition.get(0)
        val intent = Intent(this, DictionaryActivity::class.java)
        intent.putExtra(KEY, firstShortDefinition.toString())
        startActivity(intent)
    }
    private fun getUrl() : String{
        val word= findViewById<EditText>(R.id.word_edit_text).text
        val apiKey = "b87b1b7d-d9a3-49ec-97bc-e858229e2084"
        val url =
                "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$apiKey"

        return url
    }
}