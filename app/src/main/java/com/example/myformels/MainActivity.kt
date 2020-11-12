package com.example.myformels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.ToolbarWidgetWrapper
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, firstView, R.string.open, R.string.close)
        firstView.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener {

          when(it.itemId){
              R.id.Fach1 -> Toast.makeText(applicationContext,"clicked Math",Toast.LENGTH_LONG).show()
              R.id.Fach2 -> Toast.makeText(applicationContext,"clicked Phy",Toast.LENGTH_LONG).show()
              R.id.Fach3 -> Toast.makeText(applicationContext,"clicked Chemie",Toast.LENGTH_LONG).show()
              R.id.Fach4 -> Toast.makeText(applicationContext,"clicked Elektrotechnik",Toast.LENGTH_LONG).show()

          }
            true
        }
        //TODO("implement the real pages")
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
         return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search,menu)

        val search=menu?.findItem(R.id.menu_search)
        val searchView= search?.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = "Formeln suchen"
        return super.onCreateOptionsMenu(menu)
    }
}