package com.example.kediuygulamasi.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kediuygulamasi.model.Cat
import com.example.kediuygulamasi.util.ImageTypeConverter

@Database(entities = arrayOf(Cat::class), version = 1)
@TypeConverters(ImageTypeConverter::class)
abstract class CatDatabase : RoomDatabase(){

    abstract fun catDao() : CatDao

    companion object{
        @Volatile var instance : CatDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, CatDatabase::class.java, "catdatabase"
        ).addTypeConverter(ImageTypeConverter())
            .build()
    }

}