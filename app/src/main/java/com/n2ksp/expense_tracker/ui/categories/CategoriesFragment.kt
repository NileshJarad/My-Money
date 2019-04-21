package com.n2ksp.expense_tracker.ui.categories


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.jakewharton.rxbinding2.widget.textChanges
import com.n2ksp.expense_tracker.R
import com.n2ksp.expense_tracker.utils.CategoryDiffUtilCallback
import com.n2ksp.expense_tracker.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_categories.*
import java.util.concurrent.TimeUnit


class CategoriesFragment : Fragment() {

    private lateinit var viewModel: CategoryViewModel
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoriesRecyclerView.layoutManager = GridLayoutManager(context, 3)

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(context!!, R.drawable.divider_open_source)
        drawable?.let {
            dividerItemDecoration.setDrawable(it)
        }

        categoriesRecyclerView.addItemDecoration(dividerItemDecoration)

        val adapter = CategoriesAdapter()

        viewModel = ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        adapter.attachCategories(viewModel.getCategories(Constants.EXPENSE))
        categoriesRecyclerView.adapter = adapter

        searchEditText.
            textChanges().
            debounce(200, TimeUnit.MILLISECONDS)
            .subscribe {
                viewModel
                    .search(it.toString())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        val diffResult = DiffUtil.calculateDiff(CategoryDiffUtilCallback(viewModel.oldFilteredCategories, viewModel.filteredCategories))
                        viewModel.oldFilteredCategories.clear()
                        viewModel.oldFilteredCategories.addAll(viewModel.filteredCategories)
                        diffResult.dispatchUpdatesTo(categoriesRecyclerView?.adapter as CategoriesAdapter)
                    }.addTo(disposable)
            }.addTo(disposable)

        expenseOrIncomeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->

            searchEditText.text = null

            if (isChecked) {
                buttonView.setTextColor(ContextCompat.getColor(context!!, R.color.colorExpense))
                buttonView.text = "Expense"
                adapter.attachCategories(viewModel.getCategories(Constants.EXPENSE))
                adapter.notifyDataSetChanged()
            } else {
                buttonView.setTextColor(ContextCompat.getColor(context!!, R.color.colorIncome))
                buttonView.text = "Income"
                adapter.attachCategories(viewModel.getCategories(Constants.INCOME))
                adapter.notifyDataSetChanged()
            }


        }
    }

}
