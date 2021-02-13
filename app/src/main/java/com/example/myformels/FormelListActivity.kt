package com.example.myformels

import Database.FachDB
import Database.Formel_Entity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fach_group_list_view.*
import kotlinx.android.synthetic.main.formel_list_view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class FormelListActivity() : AppCompatActivity(), OnGroupClickListener {

    internal var data: ArrayList<FormelGroup>? = null
    internal var adapter: FormelListViewAdapter? = null

    var Fach: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formel_list_view)
        Fach = intent.getStringExtra("Fach")

        data = fechData()
        adapter = FormelListViewAdapter(this, data!!,this)
        formelsListRv.adapter = adapter
        formelsListRv.layoutManager = LinearLayoutManager(this)


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