package com.example.myformels

import Database.FachDB
import Database.Formel_Entity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myformels.MathFormListAdapter
import com.example.myformels.R
import kotlinx.android.synthetic.main.math_form_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import java.util.ArrayList

class MathFormListActivity : AppCompatActivity() {

    internal var data: ArrayList<Formel>? = null
    internal var SelectedMathForm: BooleanArray= BooleanArray(200)
    internal var adapter: MathFormListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.math_form_list)

        data = chargeLesDonnees()
        adapter = MathFormListAdapter(this, data!!, SelectedMathForm)
        mathFormelsListRv.adapter = adapter
        mathFormelsListRv.layoutManager = LinearLayoutManager(this)

    }


    private fun chargeLesDonnees(): ArrayList<Formel> {
        val retVal = ArrayList<Formel>()


        GlobalScope.launch {
            val db: FachDB = FachDB.getDatabase(applicationContext)
            var list:List<Formel_Entity> = db.fachDAO().getAll()

            runOnUiThread{
                list.forEach{
                    retVal.add(Formel(it.formelName,it.formelText))
                    //Toast.makeText(applicationContext,it.formelName,Toast.LENGTH_LONG).show()

                }
            }

        }
        /*
            retVal.add(MathFormel("MathFormel1", "description"))
            retVal.add(MathFormel("MathFormel2", "description"))
            retVal.add(MathFormel("MathFormel3", "description"))
            retVal.add(MathFormel("MathFormel4", "description"))
            retVal.add(MathFormel("MathFormel5", "description"))
            retVal.add(MathFormel("MathFormel3", "description"))
            retVal.add(MathFormel("MathFormel7", "description"))

        */
        do {

            Thread.sleep(5000)
        }while (retVal==null)

        Toast.makeText(application,retVal[0].name,Toast.LENGTH_SHORT)

        return retVal
    }
}