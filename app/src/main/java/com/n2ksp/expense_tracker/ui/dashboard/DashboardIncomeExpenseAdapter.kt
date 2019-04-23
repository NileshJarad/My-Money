package com.n2ksp.expense_tracker.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.utils.AmountUtils
import com.n2ksp.expense_tracker.utils.Constants
import javax.inject.Inject

class DashboardIncomeExpenseAdapter @Inject constructor() :
    RecyclerView.Adapter<DashboardIncomeExpenseAdapter.ViewHolder>() {


    private var incomeExpenseModelList = ArrayList<IncomeExpenseModel>()

    fun addAllData(incomeExpenseModelList: ArrayList<IncomeExpenseModel>) {
        this.incomeExpenseModelList = incomeExpenseModelList
        notifyDataSetChanged()
    }

    fun addData(incomeExpenseModel: IncomeExpenseModel) {
        this.incomeExpenseModelList.add(incomeExpenseModel)
        notifyItemInserted(incomeExpenseModelList.size - 1)
    }

    fun getIncomeExpenseAmounts(): Pair<Float, Float> {
        var expenseAmount = 0.0f
        var incomeAmount = 0.0f

        incomeExpenseModelList.forEach {
            if (it.categoryInfoModel.categoryType == Constants.EXPENSE) {
                expenseAmount += it.amount
            } else {
                incomeAmount += it.amount
            }
        }

        return Pair(incomeAmount, expenseAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard_income_expense, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return incomeExpenseModelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val incomeExpenseModel = incomeExpenseModelList[position]
        val color = if (incomeExpenseModel.categoryInfoModel.categoryType == Constants.INCOME) {
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.colorIncome
            )

        } else {
            ContextCompat.getColor(
                holder.itemView.context,
                R.color.colorExpense
            )

        }

        holder.incomeOrExpenseIndicatorView.setBackgroundColor(
            color
        )

        holder.incomeOrExpenseAmount.setTextColor(
            color
        )

        holder.incomeOrExpenseAmount.text = AmountUtils.getAmountFormatted(incomeExpenseModel.amount)
        holder.memoTextView.text = incomeExpenseModel.memo
        holder.incomeOrExpenseCategoryTextView.text = incomeExpenseModel.categoryInfoModel.categoryTitle
        holder.incomeOrExpenseCategoryTextView.text = incomeExpenseModel.categoryInfoModel.categoryTitle
        holder.incomeOrExpenseImageView.setImageResource(incomeExpenseModel.categoryInfoModel.categoryImage)
        holder.incomeOrExpenseImageView.setColorFilter(
            ContextCompat.getColor(
                holder.itemView.context,
                incomeExpenseModel.categoryInfoModel.categoryColor
            )
        )

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var incomeOrExpenseCategoryTextView: TextView = itemView.findViewById(R.id.incomeOrExpenseCategoryTextView)
        var incomeOrExpenseIndicatorView: View = itemView.findViewById(R.id.incomeOrExpenseIndicatorView)
        var incomeOrExpenseAmount: TextView = itemView.findViewById(R.id.incomeOrExpenseAmount)
        var memoTextView: TextView = itemView.findViewById(R.id.topLinearLayout)
        var incomeOrExpenseImageView: ImageView = itemView.findViewById(R.id.incomeOrExpenseImageView)

    }
}
