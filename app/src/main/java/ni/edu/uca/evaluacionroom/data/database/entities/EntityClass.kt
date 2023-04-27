package ni.edu.uca.evaluacionroom.data.database.entities

import androidx.room.*

@Entity(tableName = "table1")
data class EntityClass(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "column1")
    val column1: String,
    @ColumnInfo(name = "column2")
    val column2: String,
    @ColumnInfo(name = "column3")
    val column3: Int,
    @ColumnInfo(name = "column4")
    val column4: String
)
