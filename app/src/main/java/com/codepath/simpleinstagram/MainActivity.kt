package com.codepath.simpleinstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.codepath.simpleinstagram.LoginActivity.Companion.TAG
import com.parse.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        queryPosts()
        findViewById<Button>(R.id.postBtn).setOnClickListener {
            val description = findViewById<EditText>(R.id.descriptionTV).text.toString()
            val user = ParseUser.getCurrentUser()
            submitPost(description, user)
        }

        findViewById<Button>(R.id.takePicBtn).setOnClickListener {

        }
    }


    fun submitPost(description: String, user: ParseUser) {

        val post = Post()
        post.setDescription(description)
        post.setUser(user)
        post.saveInBackground { exception ->
            if (exception != null) {
                Log.e(TAG, "Error while saving post")
                exception.printStackTrace()
            } else {
                Log.e(TAG, "Success")

            }
        }  }

        fun queryPosts() {
            val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
            query.findInBackground { posts, e ->
                if (e != null) {
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "Post: " + post.getDescription())
                        }
                    }

                }
            }
        }


        companion object {
            const val TAG = "MainActivity"
        }
}