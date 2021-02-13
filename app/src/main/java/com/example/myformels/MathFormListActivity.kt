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


class MathFormListActivity() : AppCompatActivity(), OnGroupClickListener {

    internal var data: ArrayList<FormelGroup>? = null
    internal var adapter: MathFormListAdapter? = null

    var Fach: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.math_form_list)
        Fach = intent.getStringExtra("Fach")

        data = fechData()
        adapter = MathFormListAdapter(this, data!!,this)
        mathFormelsListRv.adapter = adapter
        mathFormelsListRv.layoutManager = LinearLayoutManager(this)


    }


    private fun fechData(): ArrayList<FormelGroup> {
        val retVal = ArrayList<FormelGroup>()

        GlobalScope.launch {
            val db: FachDB = FachDB.getDatabase(applicationContext)
            val list:List<Formel_Entity> = db.fachDAO().getAll()

            runOnUiThread{
                list.forEach{

                    if(it.formelFach==Fach) {
                        val formelGroup = it.formelFachGroup
                        if (retVal.isEmpty()) {
                            retVal.add(FormelGroup(it.formelFachGroup))
                        } else {
                            var _alreadyin = false
                            retVal.forEach {
                                if (it.name?.toLowerCase() == formelGroup.toLowerCase()) {
                                    _alreadyin = true
                                }
                            }
                            if (!_alreadyin) {
                                retVal.add(FormelGroup(formelGroup))
                            }
                        }
                    }

                }
            }

        }

        return retVal
    }

    override fun onGroupItemClicked(position: Int) {
        Toast.makeText(this ,data!![position].name, Toast.LENGTH_SHORT).show()
    }
}