package com.example.myformels

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.math_form_list_item.view.*
import java.util.ArrayList

class MathFormListAdapter(
    cxt: Activity,
    private val data: ArrayList<MathFormel>,
    private val checkBoxState: BooleanArray
)  : RecyclerView.Adapter<MathFormListAdapter.VH>() {


    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTxt: TextView
        var descriptionTxt: TextView

        init {

            nameTxt = itemView.MathFormlistActivityItemname
            descriptionTxt = itemView.MathFormlistActivityItemdescription
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private val inflater: LayoutInflater

    init {
        this.inflater = cxt.layoutInflater
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): VH {
        val view = inflater.inflate(R.layout.math_form_list_item, null)

        return VH(view)
    }

    override fun onBindViewHolder(vh: VH, i: Int) {
        vh.nameTxt.text = data[i].name
        vh.descriptionTxt.text = data[i].description

        }
    }
