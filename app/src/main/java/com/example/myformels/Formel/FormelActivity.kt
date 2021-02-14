package com.example.myformels.FormelFachGroup

import Database.FachDB
import Database.Formel_Entity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myformels.FormelListView.FormelListActivity
import com.example.myformels.R
import kotlinx.android.synthetic.main.fach_group_list_view.*
import kotlinx.android.synthetic.main.fach_group_list_view.mathFormelsListRv
import kotlinx.android.synthetic.main.formel_view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class FormelActivity() : AppCompatActivity(), OnGroupClickListener {

    internal var data: ArrayList<FormelGroup>? = null
    internal var adapter: FormelAdapter? = null

    var Fach: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.formel_view_item)
        Fach = intent.getStringExtra("Fach")

        data = fechData()
        adapter = FormelAdapter(this, data!!,this)
        formelRv.adapter = adapter
        formelRv.layoutManager = LinearLayoutManager(this)

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
        val intent = Intent(this, FormelListActivity::class.java)
        intent.putExtra("FachGroup",data!![position].name)
        startActivity(intent)

    }
}