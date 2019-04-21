package com.n2ksp.expense_tracker.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.CategoryInfoModel
import kotlinx.android.synthetic.main.item_categoris.view.*
import timber.log.Timber
import java.lang.Exception

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    lateinit var categories : MutableList<CategoryInfoModel>

    fun attachCategories(categories : MutableList<CategoryInfoModel>){
        this.categories = categories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_categoris, parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(category = categories[position])
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(category : CategoryInfoModel){
            Timber.e("bind : ${category.categoryImage} ${category.categoryTitle} ")
            try {
                itemView.catgegoryImageView.setImageResource(category.categoryImage)
            } catch (e : Exception){
                e.printStackTrace()
            }
            itemView.categoryTextVIew.text = category.categoryTitle
        }
    }

}
