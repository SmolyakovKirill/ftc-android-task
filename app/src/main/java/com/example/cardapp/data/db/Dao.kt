package com.example.cardapp.data.db
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertItem(item: RequestCard)
    @Query("SELECT * FROM requests ORDER BY id DESC")
    fun getAllRequests(): Flow<List<RequestCard>>
    @Query("SELECT COUNT(*) FROM requests")
    fun getTableSize(): Int
    @Query("DELETE FROM requests WHERE id IN (SELECT id FROM requests ORDER BY id LIMIT 1)")
    fun deleteLastRequest()
}