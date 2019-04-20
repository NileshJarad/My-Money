package com.n2ksp.expense_tracker.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.CategoryInfoModel

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    lateinit var categories : ArrayList<CategoryInfoModel>

    fun attachCategories(categories : ArrayList<CategoryInfoModel>){
        this.categories = categories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_categoris, parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = categories.get(position)
        holder.categoryImageView.setImageResource(model.categoryImage)
        holder.categoryTitleTextView.text = model.categoryTitle
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryImageView: ImageView = itemView.findViewById(R.id.catgegoryImageView)
        var categoryTitleTextView: TextView = itemView.findViewById(R.id.categoryTextVIew)
    }

}
