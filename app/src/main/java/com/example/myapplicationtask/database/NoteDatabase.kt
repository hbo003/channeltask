package com.example.myapplicationtask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplicationtask.dao.UserDao
import com.example.myapplicationtask.model.DataConverter
import com.example.myapplicationtask.model.Logo
import com.example.myapplicationtask.model.LogoConverter
import com.example.myapplicationtask.model.LogoItem
import com.example.myapplicationtask.model.Options
import com.example.myapplicationtask.model.Product
import com.example.myapplicationtask.model.User

@TypeConverters(value = [DataConverter::class, LogoConverter::class])
@Database(
    entities = [User::class, Product::class, Options::class, LogoItem::class, Logo::class],
    version = 6,
    exportSchema = true
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNotesDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_databaseseven"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}