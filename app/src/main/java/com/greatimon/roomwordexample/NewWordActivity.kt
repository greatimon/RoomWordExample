package com.greatimon.roomwordexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_word.*

/**
 * Created by JYN on 2020-02-27
 *
 *
 */
class NewWordActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_new_word)
		
		button_save.setOnClickListener {
			val replyIntent = Intent()
			when (TextUtils.isEmpty(edit_word.text)) {
				true -> {
					setResult(Activity.RESULT_CANCELED, replyIntent)
				}
				false -> {
					val word = edit_word.text.toString()
					replyIntent.putExtra(EXTRA_REPLY, word)
					setResult(Activity.RESULT_OK, replyIntent)
				}
			}
			finish()
		}
	}
	
	companion object {
		const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
	}
}