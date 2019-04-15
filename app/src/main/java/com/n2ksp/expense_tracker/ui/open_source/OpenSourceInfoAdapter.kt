package com.n2ksp.expense_tracker.ui.open_source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.OpenSourceInfoModel
import javax.inject.Inject

class OpenSourceInfoAdapter @Inject constructor() : RecyclerView.Adapter<OpenSourceInfoAdapter.ViewHolder>() {


    lateinit var openSourceInfoList: ArrayList<OpenSourceInfoModel>

    fun addAllData(openSourceInfoList: ArrayList<OpenSourceInfoModel>) {
        this.openSourceInfoList = openSourceInfoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_open_source_info, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return openSourceInfoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.libTitleTextView.text = openSourceInfoList[position].libName

        holder.typeTextView.text = openSourceInfoList[position].type

        val license = openSourceInfoList[position].license

        if (license.isEmpty()) {
            holder.licenseTextView.visibility = View.GONE
        } else {
            holder.licenseTextView.visibility = View.VISIBLE
            holder.licenseTextView.text = license
        }
        holder.copyrightTextView.text = openSourceInfoList[position].copyright
        holder.websiteTextView.text = openSourceInfoList[position].website
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var libTitleTextView: TextView = itemView.findViewById(R.id.libTitleTextView)
        var typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        var copyrightTextView: TextView = itemView.findViewById(R.id.copyrightTextView)
        var licenseTextView: TextView = itemView.findViewById(R.id.licenseTextView)
        var websiteTextView: TextView = itemView.findViewById(R.id.websiteTextView)

    }

}
