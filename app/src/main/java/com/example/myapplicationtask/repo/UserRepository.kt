package com.example.myapplicationtask.repo

import androidx.lifecycle.LiveData
import com.example.myapplicationtask.dao.UserDao
import com.example.myapplicationtask.model.Logo
import com.example.myapplicationtask.model.Options
import com.example.myapplicationtask.model.User

class UserRepository(private val notesDao: UserDao) {


    /**
     * user repo
     */
    val userData: LiveData<User> = notesDao.getUser()
    suspend fun insert(user: User) {
        notesDao.insert(user)
    }

    suspend fun update(user: User) {
        notesDao.update(user)
    }

    /**
     * product repo
     */
    val productData: LiveData<String> = notesDao.getAllProduct()
    suspend fun insertProduct(product: Options) {
        notesDao.insertProduct(product)
    }

    /**
     * Item repo
     */
    val logoData: LiveData<String> = notesDao.getAllLogo()
    suspend fun insertLogo(logo: Logo) {
        notesDao.insertLogo(logo)
    }


}