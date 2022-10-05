package com.example.myapplicationtask.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

/**
 * user model
 */
@Entity(tableName = "userTable")
class User(
    @ColumnInfo(name = "firstname") var userFirstName: String,
    @ColumnInfo(name = "lastname") var userLastName: String,
    @ColumnInfo(name = "mobilenumber") var userMobileNumber: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "password") var password: String,
    @ColumnInfo(name = "company") var company: String
) {
    @PrimaryKey(autoGenerate = false)
    var id = 0

}





