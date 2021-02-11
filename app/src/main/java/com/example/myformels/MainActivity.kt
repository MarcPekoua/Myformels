package com.example.myformels

import Database.FachDB
import Database.Formel_Entity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.ToolbarWidgetWrapper
import androidx.room.Room
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.math_form_list.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var adapter : ArrayAdapter<*>
    lateinit var adapterLeer : ArrayAdapter<*>
    var listTest : MutableList<String> = mutableListOf()
    var listLeer : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listTest.add("Formel1")
        listTest.add("Formel2")
        listTest.add("Formel3")
        listTest.add("Formel4")
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,listTest )
        adapterLeer = ArrayAdapter(this,android.R.layout.simple_list_item_1,listLeer )
        toggle = ActionBarDrawerToggle(this, firstView, R.string.open, R.string.close)
        firstView.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        nav_view.setNavigationItemSelectedListener {

          when(it.itemId){
              R.id.math -> Toast.makeText(applicationContext,"clicked Math",Toast.LENGTH_LONG).show()
              R.id.physic -> Toast.makeText(applicationContext,"clicked Phy",Toast.LENGTH_LONG).show()
              R.id.Fach3 -> Toast.makeText(applicationContext,"clicked Chemie",Toast.LENGTH_LONG).show()
              R.id.Fach4 -> Toast.makeText(applicationContext,"clicked Elektrotechnik",Toast.LENGTH_LONG).show()

          }
            true

        }
        //TODO("implement the real pages")
        fach1.setOnClickListener {
            val intent = Intent(this, MathFormListActivity::class.java)
            startActivity(intent)
        }
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

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            //TODO("Style must be changed")
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == ""){
                    list_search_view.adapter=adapterLeer
                }
                else{
                    list_search_view.adapter=adapter
                    adapter.filter.filter(newText)
                }
                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }




}



