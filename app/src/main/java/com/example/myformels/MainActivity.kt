package com.example.myformels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
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
        navView.setNavigationItemSelectedListener {

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
}