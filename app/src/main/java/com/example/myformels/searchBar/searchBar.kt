package com.example.myformels.searchBar

import androidx.appcompat.widget.Toolbar
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myformels.R
import kotlinx.android.synthetic.main.search_bar.*

class searchBar:Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        test.setOnLongClickListener {
            Toast.makeText(context,"clicked Math", Toast.LENGTH_LONG).show()
            true
        }
    }
}