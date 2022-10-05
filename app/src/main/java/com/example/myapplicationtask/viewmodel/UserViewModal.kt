package com.example.myapplicationtask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplicationtask.database.NoteDatabase
import com.example.myapplicationtask.model.Logo
import com.example.myapplicationtask.model.Options
import com.example.myapplicationtask.model.User
import com.example.myapplicationtask.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModal(application: Application) : AndroidViewModel(application) {
    val userData: LiveData<User>
    val allProduct: LiveData<String>
    val allItem: LiveData<String>
    val repository: UserRepository

    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = UserRepository(dao)
        userData = repository.userData
        allProduct = repository.productData
        allItem = repository.logoData
    }

    /**
     * user view model
     */
    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(user)
    }

    fun addNote(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(user)
    }

    /**
     * product view modle
     */
    fun addProduct(product: Options) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertProduct(product)
    }

    /**
     * Item view modle
     */
    fun addItem(item: Logo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertLogo(item)
    }

}