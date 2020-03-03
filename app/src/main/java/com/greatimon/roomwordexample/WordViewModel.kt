package com.greatimon.roomwordexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.greatimon.roomwordexample.Word
import com.greatimon.roomwordexample.WordRepository
import com.greatimon.roomwordexample.WordRoomDatabase
import kotlinx.coroutines.launch

/**
 * Created by JYN on 2020-02-27
 *
 *
 */
class WordViewModel(application: Application) : AndroidViewModel(application) {
	
	// 데이터를 얻기 위해 Repository에 대한 참조를 유지
	private val repository: WordRepository
	val allWords: LiveData<List<Word>>
	
	init {
		val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
		repository = WordRepository(wordsDao)
		allWords = repository.allWords
	}
	
	/**
	 * The implementation of insert() in the database is completely hidden from the UI.
	 * Room ensures that you're not doing any long running operations on
	 * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
	 * ViewModels have a coroutine scope based on their lifecycle called
	 * viewModelScope which we can use here.
	 *
	 * (번역기 돌림)
	 * 데이터베이스에서 insert () 구현은 UI에서 완전히 숨겨져 있습니다.
	 * Room은 메인 스레드에서 오래 실행되는 작업을 수행하지 않고 UI를 차단하므로 변경되는 Dispatcher를 처리 할 필요가 없습니다.
	 * ViewModel에는 여기에서 사용할 수있는 viewModelScope라는 수명주기에 따라 코 루틴 범위가 있습니다.
	 */
	fun insert(word: Word) = viewModelScope.launch {
		repository.insert(word)
	}
}