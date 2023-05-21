package org.d3if3003.novelstine.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun getUser(): LiveData<List<User>>
    @Query("DELETE FROM user")
    fun clearData()

}