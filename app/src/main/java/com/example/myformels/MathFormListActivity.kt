package com.example.myformels

import Database.FachDB
import Database.Formel_Entity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.math_form_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


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
            val list:List<Formel_Entity> = db.fachDAO().getAll()

            runOnUiThread{
                list.forEach{
                    retVal.add(Formel(it.formelName, it.formelText))
                    Toast.makeText(applicationContext, retVal[0].name, Toast.LENGTH_LONG).show()

                }
            }

        }
        /*
            retVal.add(Formel("MathFormel1", "description"))
            retVal.add(Formel("MathFormel2", "description"))
            retVal.add(Formel("MathFormel3", "description"))
            retVal.add(Formel("MathFormel4", "description"))
            retVal.add(Formel("MathFormel5", "description"))
            retVal.add(Formel("MathFormel3", "description"))
            retVal.add(Formel("MathFormel7", "description"))

        */
        return retVal
    }
}