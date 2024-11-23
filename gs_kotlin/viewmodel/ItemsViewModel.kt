package br.com.fiap.gs_kotlin.viewmodel

import ItemDatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import br.com.fiap.gs_kotlin.data.ItemDao
import br.com.fiap.gs_kotlin.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val itemDao: ItemDao

    val itemsLiveData: LiveData<List<ItemModel>>

    init {
        val database = Room.databaseBuilder(
            getApplication<Application>(),
            ItemDatabase::class.java,
            "items_database"
        ).build()

        itemDao = database.itemDao()

        itemsLiveData = itemDao.getAll()
    }


    fun addItem(tip: String, description: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newItem = ItemModel(tip = tip, description = description)
            itemDao.insert(newItem)
        }
    }

    fun removeItem(item: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            itemDao.delete(item)
        }
    }
}