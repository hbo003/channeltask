package com.example.myapplicationtask.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplicationtask.model.Logo
import com.example.myapplicationtask.model.Options
import com.example.myapplicationtask.model.User

@Dao
interface UserDao {

    /**
     * user dao
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("Select * from userTable where id = 0")
    fun getUser(): LiveData<User>

    /**
     * product dao
     */
    @Query("Select option_values from options where id = 1")
    fun getAllProduct(): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Options)

    /**
     * Item dao
     */
    @Query("Select option_values from logo where id = 1")
    fun getAllLogo(): LiveData<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLogo(logo: Logo)


}