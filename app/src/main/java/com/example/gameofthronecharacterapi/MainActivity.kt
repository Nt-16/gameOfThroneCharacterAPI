package com.example.gameofthronecharacterapi

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.random.Random


class MainActivity : AppCompatActivity() {


    private var imageUrl = ""
    private var imageTitleName = ""
    private var characterName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myButton = findViewById<Button>(R.id.btnext)
        val myImage = findViewById<ImageView>(R.id.ivImage)
        val myTitle = findViewById<TextView>(R.id.tvtitle)
        val myName = findViewById<TextView>(R.id.tvname)


        myButton.setOnClickListener() {
            getttingImage()

            myTitle.text = imageTitleName
            myName.text = characterName



            Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(myImage)




        }
    }

    private fun getttingImage() {
        val random = Random.nextInt(from = 1, until = 52)
        val client = AsyncHttpClient()

        Log.d("randomnumcheck", "random number is $random")

        client["https://thronesapi.com/api/v2/Characters/$random", object :
            JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.d("Image", "error occurred")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.d("Image", "looks good")
                imageUrl = json.jsonObject.getString("imageUrl")
                imageTitleName = json.jsonObject.getString("title")
                characterName = json.jsonObject.getString("fullName")



            }


        }]


    }
}