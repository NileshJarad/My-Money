package com.n2ksp.expense_tracker.ui.about_us

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.AboutUsModel
import com.n2ksp.expense_tracker.data.model.OpenSourceInfoModel
import javax.inject.Inject

class AboutUsAdapter @Inject constructor() : RecyclerView.Adapter<AboutUsAdapter.ViewHolder>() {


    lateinit var contributorList: ArrayList<AboutUsModel>

    fun addAllData(contributorList: ArrayList<AboutUsModel>) {
        this.contributorList = contributorList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_about_us, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return contributorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.contributorNameTextView.text = contributorList[position].contributorName

        holder.contributoDescTextView.text = contributorList[position].contributorDesc

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contributorNameTextView: TextView = itemView.findViewById(R.id.contributorNameTextView)
        var contributoDescTextView: TextView = itemView.findViewById(R.id.contributoDescTextView)


    }

}
