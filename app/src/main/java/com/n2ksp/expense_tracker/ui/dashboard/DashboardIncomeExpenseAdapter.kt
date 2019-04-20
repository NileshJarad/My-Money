package com.n2ksp.expense_tracker.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.data.model.OpenSourceInfoModel
import com.n2ksp.expense_tracker.utils.AmountUtils
import javax.inject.Inject

class DashboardIncomeExpenseAdapter @Inject constructor() :
    RecyclerView.Adapter<DashboardIncomeExpenseAdapter.ViewHolder>() {


    lateinit var openSourceInfoList: ArrayList<OpenSourceInfoModel>

    fun addAllData(openSourceInfoList: ArrayList<OpenSourceInfoModel>) {
        this.openSourceInfoList = openSourceInfoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_dashboard_income_expense, parent, false)
        )
    }

    override fun getItemCount(): Int {
//        return openSourceInfoList.size
        return 15
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var color = if (position % 2 == 0) {
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

        holder.incomeOrExpenseAmount.text = AmountUtils.getAmountFormatted(120f)

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var incomeOrExpenseIndicatorView: View = itemView.findViewById(R.id.incomeOrExpenseIndicatorView)
        var incomeOrExpenseAmount: TextView = itemView.findViewById(R.id.incomeOrExpenseAmount)

    }
}
