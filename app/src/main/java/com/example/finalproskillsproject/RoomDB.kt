package com.example.finalproskillsproject

import android.content.Context
import androidx.room.*

@Database(entities = [Person::class], version=1)
abstract class DB:RoomDatabase(){
    abstract fun getMyDAO():MyDao

    companion object{
        @Volatile
        private var INSTANCE:DB?=null

        fun getInstance(context: Context): DB {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, DB::class.java, "person_db")
                    .build()
                return INSTANCE!!
            }

        }
    }
}


@Entity
data class Person(
    @PrimaryKey (autoGenerate = true) val id:Int,
    val name:String,
    val surname:String,
    val balance:Long,
    val cashBack: Long,
    val numberOfCards:Int
)


@Dao
interface MyDao{
    @Query ("SELECT * FROM Person WHERE id=:id")
    fun getLoggedUser(id:Int)
}