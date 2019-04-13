package com.n2ksp.expense_tracker.ui.open_source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.OpenSourceInfoModel

class OpenSourceInfoAdapter constructor(var openSourceInfoList: ArrayList<OpenSourceInfoModel>) :
    RecyclerView.Adapter<OpenSourceInfoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_open_source_info, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return openSourceInfoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvLibTitle.text = openSourceInfoList[position].libName
        holder.tvLicense.text = openSourceInfoList[position].license
        holder.tvCopyright.text = openSourceInfoList[position].copyright
        holder.tvWebsite.text = openSourceInfoList[position].website
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvLibTitle: TextView = itemView.findViewById(R.id.tvLibTitle)
        var tvCopyright: TextView = itemView.findViewById(R.id.tvCopyright)
        var tvLicense: TextView = itemView.findViewById(R.id.tvLicense)
        var tvWebsite: TextView = itemView.findViewById(R.id.tvWebsite)

    }

}
