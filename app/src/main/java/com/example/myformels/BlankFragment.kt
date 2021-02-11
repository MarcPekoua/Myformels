package com.example.myformels

import Database.FachDB
import Database.Formel_Entity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.math_form_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class BlankFragment : Fragment() {

    internal var data: ArrayList<Formel>? = null
    internal var SelectedMathForm: BooleanArray= BooleanArray(200)
    internal var adapter: MathFormListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        data = chargeLesDonnees()
        adapter = MathFormListAdapter(this.requireActivity(), data!!, SelectedMathForm)
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        super.onViewCreated(view, savedInstanceState)
    }
    private fun chargeLesDonnees(): ArrayList<Formel> {
        val retVal = ArrayList<Formel>()

        val ctx=this.requireContext()

        GlobalScope.launch {
            val db: FachDB = FachDB.getDatabase(ctx)
            val list:List<Formel_Entity> = db.fachDAO().getAll()

            requireActivity().runOnUiThread{
                list.forEach{
                    retVal.add(Formel(it.formelName,it.formelText))
                    Toast.makeText(ctx,retVal[0].name, Toast.LENGTH_LONG).show()

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