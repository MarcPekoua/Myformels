package com.example.myformels.FormelListView

import Database.FachDB
import Database.Formel_Entity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myformels.FormelActivity.FormelActivity
import com.example.myformels.FormelFachGroup.FormelGroup
import com.example.myformels.R
import kotlinx.android.synthetic.main.formel_list_view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import java.util.*


class FormelListActivity() : AppCompatActivity(), OnListClickListener {

    internal var data: ArrayList<FormelGroup>? = null
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


    private fun fechData(): ArrayList<FormelGroup> {
        val retVal = ArrayList<FormelGroup>()

        GlobalScope.launch {
            val db: FachDB = FachDB.getDatabase(applicationContext)
            val list:List<Formel_Entity> = db.fachDAO().getAll()


            runOnUiThread{
                list.forEach{

                    if(it.formelFachGroup==FachGroup) {
                        val formel = it.formelName
                        if (retVal.isEmpty()) {
                            retVal.add(FormelGroup(it.formelName))
                        } else {
                            var _alreadyin = false
                            retVal.forEach {
                                if (it.name?.toLowerCase() == formel.toLowerCase()) {
                                    _alreadyin = true
                                }
                            }
                            if (!_alreadyin) {
                                retVal.add(FormelGroup(formel))
                            }
                        }
                    }

                }
            }

        }

        return retVal
    }


    override fun onListClickListener(position: Int) {
        startActivity<FormelActivity>()
        val intent = Intent(this, FormelActivity::class.java)
        intent.putExtra("Formel",data!![position].name)
        startActivity(intent)
    }

}