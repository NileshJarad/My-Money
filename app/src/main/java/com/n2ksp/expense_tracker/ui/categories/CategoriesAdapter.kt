package com.n2ksp.expense_tracker.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.CategoryInfoModel
import kotlinx.android.synthetic.main.item_categoris.view.*

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var allowSelection: Boolean = false
    private lateinit var categories: MutableList<CategoryInfoModel>
    private var lastCategoryCheckedId = -99

    fun attachCategories(categories: MutableList<CategoryInfoModel>) {
        this.categories = categories
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_categoris, parent, false))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.itemView.categoryImageView.setImageResource(category.categoryImage)

        var color = ContextCompat.getColor(
            holder.itemView.context,
            R.color.grey_600
        )

        if (category.selected) {
            color = ContextCompat.getColor(
                holder.itemView.context,
                category.categoryColor
            )
        }

        holder.itemView.categoryImageView.setColorFilter(color)
        holder.itemView.categoryTextVIew.setTextColor(color)

        holder.itemView.categoryTextVIew.text = category.categoryTitle


        if (allowSelection) {
            holder.itemView.deleteImageButton.visibility = View.INVISIBLE

            holder.itemView.setOnClickListener {
                val adapterPosition = holder.adapterPosition

                val indexOf = categories.indexOf(
                    CategoryInfoModel(
                        categoryId = lastCategoryCheckedId,
                        categoryColor = -99,
                        categoryImage = -99,
                        categoryTitle = "",
                        categoryType = ""
                    )
                )


                if (adapterPosition != indexOf) { // checks same item is clicked,  if item is different then it updated data
                    categories[adapterPosition].selected = true
                    notifyItemChanged(adapterPosition)

                    if (indexOf >= 0) {
                        categories[indexOf].selected = false
                        notifyItemChanged(indexOf)
                    }
                    lastCategoryCheckedId = categories[adapterPosition].categoryId
                }
            }
        } else {
            holder.itemView.deleteImageButton.visibility = View.VISIBLE
        }

    }

    fun setAllowSelection(allowSelection: Boolean) {
        this.allowSelection = allowSelection
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}
