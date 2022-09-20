package com.example.lesson14roomexercices

import androidx.room.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String
)

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    fun getAll(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)
}

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}