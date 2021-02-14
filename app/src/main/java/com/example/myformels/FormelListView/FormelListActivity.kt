package com.example.myformels.FormelListView

import Database.FachDB
import Database.Formel_Entity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myformels.FormelFachGroup.FormelGroup
import com.example.myformels.R
import kotlinx.android.synthetic.main.formel_list_view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class FormelListActivity() : AppCompatActivity(), OnListClickListener {

    internal var data: ArrayList<FormelListView>? = null
    internal var adapter: FormelListViewAdapter? = null

    var FachGroup: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formel_list_view)
        FachGroup = intent.getStringExtra("FachGroup")

        data = fechData()
        adapter = FormelListViewAdapter(this, data!!,this)
        formelsListRv.adapter = adapter
        formelsListRv.layoutManager = LinearLayoutManager(this)



    }


    private fun fechData(): ArrayList<FormelListView> {
        val retVal = ArrayList<FormelListView>()

        GlobalScope.launch {
            val db: FachDB = FachDB.getDatabase(applicationContext)
            val list:List<Formel_Entity> = db.fachDAO().getAll()


            runOnUiThread{
                list.forEach{

                    if(it.formelFachGroup==FachGroup) {
                        val formel = it.formelName
                        val text= it.formelText
                        val form = it.formelForm
                        if (retVal.isEmpty()) {
                            retVal.add(FormelListView(it.formelName,it.formelText,it.formelForm))
                        } else {
                            var _alreadyin = false
                            retVal.forEach {
                                if (it.name?.toLowerCase() == formel.toLowerCase()) {
                                    _alreadyin = true
                                }
                            }
                            if (!_alreadyin) {
                                retVal.add(FormelListView(formel,text,form))
                            }
                        }
                    }

                }
            }

        }

        return retVal
    }


    override fun onListClickListener(position: Int) {
        //todo not yet implemend
    }

}