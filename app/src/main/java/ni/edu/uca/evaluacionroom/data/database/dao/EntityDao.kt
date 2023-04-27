package ni.edu.uca.evaluacionroom.data.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ni.edu.uca.evaluacionroom.data.database.entities.EntityClass

@Dao
interface EntityDao {
    @Query("SELECT * FROM table1")
    fun getEntities(): Flow<List<EntityClass>>

    @Query("SELECT * from table1 WHERE id = :id")
    fun getEntity(id: Int): Flow<EntityClass>

    //Insert, Update y Delete
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entityClass: EntityClass)

    @Update
    suspend fun update(entityClass: EntityClass)

    @Delete
    suspend fun delete(entityClass: EntityClass)
}