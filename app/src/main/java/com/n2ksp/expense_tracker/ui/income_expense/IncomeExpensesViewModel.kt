package com.n2ksp.expense_tracker.ui.income_expense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.n2ksp.expense_tracker.data.model.CategoryInfoModelCreator
import com.n2ksp.expense_tracker.data.model.IncomeExpenseModel
import com.n2ksp.expense_tracker.data.room.IncomeExpenseDBModel
import com.n2ksp.expense_tracker.utils.Constants
import com.n2ksp.expense_tracker.utils.DateUtils
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList


class IncomeExpensesViewModel(application: Application) : AndroidViewModel(application) {

    var compositeDisposable = CompositeDisposable()
    private var repository = IncomeExpensesRepository(application)

    private var list: MutableLiveData<ArrayList<IncomeExpenseModel>> = MutableLiveData()
    private var singleEntry: MutableLiveData<IncomeExpenseModel> = MutableLiveData()
    private var incomeExpense: MutableLiveData<Pair<Float, Float>> = MutableLiveData()

    fun addEntry(incomeExpense: IncomeExpenseModel) {
        val disposable: Disposable = Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.insert(incomeExpense = IncomeExpenseDBModel().apply {
                    categoryId = incomeExpense.categoryInfoModel.categoryId
                    amount = incomeExpense.amount
                    memo = incomeExpense.memo
                    date = Date(incomeExpense.date)
                    type = incomeExpense.categoryInfoModel.categoryType
                })
            }

        compositeDisposable.add(disposable)

    }

    fun updateEntry(incomeExpense: IncomeExpenseModel) {
        val disposable: Disposable = Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.update(incomeExpense = IncomeExpenseDBModel().apply {
                    id = incomeExpense.id
                    categoryId = incomeExpense.categoryInfoModel.categoryId
                    memo = incomeExpense.memo
                    amount = incomeExpense.amount
                    date = Date(incomeExpense.date)
                    type = incomeExpense.categoryInfoModel.categoryType
                })
            }

        compositeDisposable.add(disposable)

    }

    fun deleteEntry(incomeExpense: IncomeExpenseModel) {
        val disposable: Disposable = Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe {
                it.delete(incomeExpense = IncomeExpenseDBModel().apply {
                    categoryId = incomeExpense.categoryInfoModel.categoryId
                    amount = incomeExpense.amount
                    memo = incomeExpense.memo
                    date = Date(incomeExpense.date)
                    type = incomeExpense.categoryInfoModel.categoryType
                    id = incomeExpense.id
                })
            }

        compositeDisposable.add(disposable)

    }

    fun getEntry(id: Int): LiveData<IncomeExpenseModel> {
        val disposable: Disposable = Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe { repositoryInner ->
                val data = repositoryInner.getEntry(id)

                singleEntry.postValue(
                    IncomeExpenseModel(
                        categoryInfoModel = CategoryInfoModelCreator.mapToCategoryInfoModel(
                            repository.getSingleCategory(
                                data.categoryId
                            )
                        ),
                        memo = data.memo,
                        amount = data.amount,
                        date = data.date.time,
                        id = data.id
                    )
                )
            }

        compositeDisposable.add(disposable)
        return singleEntry
    }


    fun getListForDay(day: Int, month: Int): LiveData<ArrayList<IncomeExpenseModel>> {
        val dates = DateUtils.getStartAndEndDatesForDay(day = day, month = month)
        val disposable: Disposable = Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe { repositoryInner ->
                val tempList = ArrayList<IncomeExpenseModel>()
                var data = repositoryInner.getAllEntriesForDate(dates.first.time, dates.second.time)
                data.forEach {
                    tempList.add(
                        IncomeExpenseModel(
                            categoryInfoModel = CategoryInfoModelCreator.mapToCategoryInfoModel(
                                repository.getSingleCategory(
                                    it.categoryId
                                )
                            ),
                            memo = it.memo,
                            amount = it.amount,
                            date = it.date.time,
                            id = it.id
                        )
                    )
                }
                list.postValue(tempList)
            }

        compositeDisposable.add(disposable)
        return list
    }

    fun getIncomeAndExpenseTotalForMonth(month: Int): LiveData<Pair<Float, Float>> {

        val disposable: Disposable = Observable.just(repository)
            .subscribeOn(Schedulers.io())
            .subscribe { repositoryInner ->

                val startEndEndDateMonth = DateUtils.getStartAndEndDatesForDay(month)

                var incomeAmount = repositoryInner.getTotalForMonthUsingType(
                    startEndEndDateMonth.first.time,
                    startEndEndDateMonth.second.time,
                    Constants.INCOME
                )

                var expenseAmount = repositoryInner.getTotalForMonthUsingType(
                    startEndEndDateMonth.first.time,
                    startEndEndDateMonth.second.time,
                    Constants.EXPENSE
                )

                incomeExpense.postValue(Pair(incomeAmount, expenseAmount))
            }

        compositeDisposable.add(disposable)

        return incomeExpense
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }


}