package com.greatimon.roomwordexample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.greatimon.roomwordexample.Word

/**
 * Created by JYN on 2020-02-21
 *
 *
 */
@Dao
interface WordDao {
	
	@Query("select * from word_table order by word asc")
	fun getAlphabetizeWords(): LiveData<List<Word>>
	
	@Insert(onConflict = OnConflictStrategy.IGNORE)
	suspend fun insert(word: Word)
	
	@Query("delete from word_table")
	suspend fun deleteAll()
}