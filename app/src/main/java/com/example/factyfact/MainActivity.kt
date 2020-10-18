package com.example.factyfact

import android.content.Intent
import android.content.Intent.EXTRA_TEXT
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     var currentImageurl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadmeme()
    }

     private fun loadmeme()
    {
        barr.visibility = View.VISIBLE
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                currentImageurl = response.getString("url")
                Glide.with(this).load(currentImageurl).listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        barr.visibility = View.GONE
                        return false

                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        barr.visibility = View.GONE
                        return false

                    }
                    
                }).into(factImageView)

            },
            Response.ErrorListener { })

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }

    fun sharefact(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "Text/plain"
        intent.putExtra(EXTRA_TEXT,"hey check out this cool meme app !! all redit meme at one place!!")
        val chooser = Intent.createChooser(intent,"share this meme on...!!!!")
        startActivity(chooser)

    }
    fun nextfact(view: View) {
        loadmeme()


    }
}