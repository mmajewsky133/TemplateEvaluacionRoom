package ni.edu.uca.evaluacionroom.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ni.edu.uca.evaluacionroom.data.database.dao.EntityDao
import ni.edu.uca.evaluacionroom.data.database.entities.EntityClass

@Database(entities = [EntityClass::class], version = 1, exportSchema = false)
abstract class EntitiesRoomDatabase : RoomDatabase() {

    abstract fun EntityDao(): EntityDao

    companion object {
        @Volatile
        private var INSTANCE: EntitiesRoomDatabase? = null

        fun getDatabase(context: Context): EntitiesRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EntitiesRoomDatabase::class.java,
                    "entities_database"
                ).build()

                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}