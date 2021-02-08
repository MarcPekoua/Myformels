package com.example.myformels

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myformels.MathFormListAdapter
import com.example.myformels.R
import kotlinx.android.synthetic.main.math_form_list.*
import java.util.ArrayList

class MathFormListActivity : AppCompatActivity() {

    internal var data: ArrayList<MathFormel>? = null
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


    private fun chargeLesDonnees(): ArrayList<MathFormel> {
        val retVal = ArrayList<MathFormel>()

            retVal.add(MathFormel("MathFormel1", "description"))
            retVal.add(MathFormel("MathFormel2", "description"))
            retVal.add(MathFormel("MathFormel3", "description"))
            retVal.add(MathFormel("MathFormel4", "description"))
            retVal.add(MathFormel("MathFormel5", "description"))
            retVal.add(MathFormel("MathFormel3", "description"))
            retVal.add(MathFormel("MathFormel7", "description"))



        return retVal
    }
}