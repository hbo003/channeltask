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

@Entity(tableName = "logoTable")
data class LogoItem(
    @ColumnInfo(name = "title") var titleItem: String,
    @ColumnInfo(name = "photo") var photoItem: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}


@Entity(tableName = "logo")
class Logo : BaseObservable(), java.io.Serializable {
    @get:Bindable
    @ColumnInfo(name = "option_name")
    var logoName: String? = null
        get() = if (field == null) "" else field
        set(optionName) {
            field = optionName

        }

    @PrimaryKey(autoGenerate = false)
    var id = 1

    @ColumnInfo(name = "option_values")
    var logoValues // custom type object
            : List<LogoItem>? = null
        get() {
            if (field == null) {
                field = ArrayList()
                return field
            }
            return field
        }
}

class LogoConverter : Serializable {
    @TypeConverter // note this annotation
    fun fromLogoValuesList(logoValues: List<LogoItem?>?): String? {
        if (logoValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<LogoItem?>?>() {}.type
        return gson.toJson(logoValues, type)
    }

    @TypeConverter // note this annotation
    fun toLogoValuesList(logoValuesString: String?): List<LogoItem>? {
        if (logoValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object :
            TypeToken<List<LogoItem?>?>() {}.type
        return gson.fromJson<List<LogoItem>>(
            logoValuesString,
            type
        )
    }
}


/**
 * static model
 */


var mLogo = mutableListOf<LogoItem>(
    LogoItem(
        "one LogoItem",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    ),
    LogoItem(
        "two LogoItem",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    ),
    LogoItem(
        "three LogoItem",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    ),
    LogoItem(
        "four LogoItem",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    ),
    LogoItem(
        "five LogoItem",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    ),
    LogoItem(
        "six LogoItem",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    ),
    LogoItem(
        "seven Item",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU"
    )
)