package com.greatimon.roomwordexample

import androidx.lifecycle.LiveData
import com.greatimon.roomwordexample.Word
import com.greatimon.roomwordexample.WordDao

/**
 * Created by JYN on 2020-02-27
 *
 *
 */
// 생성자에서 DAO를 private으로 선언.
// DAO에만 액세스(DAO에는 데이터베이스에 대한 모든 읽기/쓰기 방법이 포함되어 있음)해야하기 때문에 전체 데이터베이스 대신 DAO를 전달한다
class WordRepository(private val wordDao: WordDao) {
	
	//' Room'은 모든 쿼리를 별도의 쓰레드에서 실행한다
	// 'observed'된 'LiveData'는 데이터가 변경되면 'Observer'에게 알린다
	val allWords: LiveData<List<Word>> = wordDao.getAlphabetizeWords()
	
	suspend fun insert(word: Word) {
		wordDao.insert(word)
	}
}