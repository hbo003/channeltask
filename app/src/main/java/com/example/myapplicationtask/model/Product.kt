package com.example.myapplicationtask.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

@Entity(tableName = "productTable")
data class Product(
    @ColumnInfo(name = "title") var titleProduct: String,
    @ColumnInfo(name = "url") var urlProduct: String,
    @ColumnInfo(name = "photo") var photoProduct: String
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}


@Entity(tableName = "options")
class Options : BaseObservable(), java.io.Serializable {
    @get:Bindable
    @ColumnInfo(name = "option_name")
    var optionName: String? = null
        get() = if (field == null) "" else field
        set(optionName) {
            field = optionName

        }
    @PrimaryKey(autoGenerate = false)
    var id = 1

    @ColumnInfo(name = "option_values")
    var optionValues // custom type object
            : List<Product>? = null
        get() {
            if (field == null) {
                field = ArrayList()
                return field
            }
            return field
        }
}
class DataConverter : Serializable {
    @TypeConverter // note this annotation
    fun fromOptionValuesList(optionValues: List<Product?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Product?>?>() {}.type
        return gson.toJson(optionValues, type)
    }

    @TypeConverter // note this annotation
    fun toOptionValuesList(optionValuesString: String?): List<Product>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<Product?>?>() {}.type
        return gson.fromJson<List<Product>>(
            optionValuesString,
            type
        )
    }
}





/**
 * static model
 */
var mproduct = mutableListOf(
    Product(
        "Learn more about new product",
        "website.com",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    ),
    Product(
        "watch more about new product",
        "website.com",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    ),
    Product(
        "About new product",
        "website.com",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    )
)