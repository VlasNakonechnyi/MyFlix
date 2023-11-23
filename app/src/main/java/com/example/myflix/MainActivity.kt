package com.example.myflix

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.myflix.databinding.FragmentMoviesBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MoviesFragment>(R.id.main_screen_fragment)
            }
        }


//        searchButton.setOnClickListener {
//            val movieListIntent = Intent(this, MovieListActivity::class.java)
//            var title = searchEditText.text.toString()
//            title = title.trim()
//            if (title.isBlank()) {
//                actionWrongInput(searchEditText)
//                return@setOnClickListener
//            }
//            Log.d(("ENTERED_TEXT"), title)
//            movieListIntent.putExtra("title", title)
//            startActivity(movieListIntent)
//        }

    }
//    private fun actionWrongInput(editText: EditText) {
//        editText.error = "title cannot be blank"
//    }





}
