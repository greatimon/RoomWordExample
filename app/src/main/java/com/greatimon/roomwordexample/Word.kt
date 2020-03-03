package com.greatimon.roomwordexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by JYN on 2020-02-21
 *
 *
 */
@Entity(tableName = "word_table")
class Word(
		@PrimaryKey
		@ColumnInfo(name = "word")
		val word: String
)

//@Entity(tableName = "word_table")
//class Word(
//
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//
//    @ColumnInfo(name = "word")
//    val word: String
//)