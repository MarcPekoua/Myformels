package com.example.myformels.FormelActivity

import Database.FachDB
import Database.Formel_Entity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myformels.FormelFachGroup.FormelGroup
import com.example.myformels.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

private var  Formel: String? = null

class FormelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formel)

        Formel = intent.getStringExtra("Formel")
        val data = fechData()
        Toast.makeText(this, data[0], Toast.LENGTH_SHORT).show()
    }

    private fun fechData(): ArrayList<String> {
        val retVal = ArrayList<String>()

        GlobalScope.launch {
            val db: FachDB = FachDB.getDatabase(applicationContext)
            val list:List<Formel_Entity> = db.fachDAO().getAll()


            runOnUiThread{
                list.forEach{
                    if (it.formelName==Formel) {
                        retVal.add(it.formelName)
                        retVal.add(it.formelText)
                        retVal.add(it.formelForm)
                    }
                }
            }

        }

        return retVal
    }


}