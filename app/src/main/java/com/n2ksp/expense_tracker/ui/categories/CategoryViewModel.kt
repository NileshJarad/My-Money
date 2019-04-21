package com.n2ksp.expense_tracker.ui.categories

/**
 * Created by SHRIKANT EKADE on 2019-04-21.
 */


import androidx.lifecycle.ViewModel
import com.n2ksp.expense_tracker.data.model.CategoryInfoModel
import com.n2ksp.expense_tracker.data.model.CategoryInfoModelCreator
import io.reactivex.Completable

class CategoryViewModel : ViewModel() {

    lateinit var originalCategories : ArrayList<CategoryInfoModel>
    val filteredCategories: MutableList<CategoryInfoModel> = mutableListOf()
    val oldFilteredCategories: MutableList<CategoryInfoModel> = mutableListOf()

    fun search(query: String): Completable = Completable.create {
        val wanted = originalCategories.filter {
            it.categoryTitle.contains(query,true) || it.categoryTitle.equals(query,true)
        }.toList()

        filteredCategories.clear()
        filteredCategories.addAll(wanted)
        it.onComplete()
    }

    fun getCategories(category : String) : MutableList<CategoryInfoModel> {
        originalCategories =
            CategoryInfoModelCreator.getCategoryInfoModel(category)
        oldFilteredCategories.clear()
        oldFilteredCategories.addAll(originalCategories)
        filteredCategories.clear()
        return oldFilteredCategories
    }

    fun searchKotlin(str: String){
        val wanted =  originalCategories.asSequence()
            .filter {
                it.categoryTitle.contains(str,true) || it.categoryTitle.equals(str,true)
            }.toList()

        filteredCategories.clear()
        filteredCategories.addAll(wanted)

    }
}