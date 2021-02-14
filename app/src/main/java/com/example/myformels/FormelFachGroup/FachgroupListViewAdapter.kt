package com.example.myformels.FormelFachGroup

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myformels.R
import kotlinx.android.synthetic.main.fach_group_list_view_item.view.*
import java.util.ArrayList

class FachgroupListViewAdapter(
    cxt: Activity,
    private val data: ArrayList<FormelGroup>,
    private val onGroupClickListener: OnGroupClickListener
    )  : RecyclerView.Adapter<FachgroupListViewAdapter.VH>() {


    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var nameTxt: TextView

        init {

            nameTxt = itemView.MathFormlistActivityItemname
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
        val view = inflater.inflate(R.layout.fach_group_list_view_item, null)

        return VH(view)
    }

    override fun onBindViewHolder(vh: VH, i: Int) {
        vh.nameTxt.text = data[i].name
        vh.itemView.setOnClickListener {
            onGroupClickListener.onGroupItemClicked(i)
        }

    }

}
