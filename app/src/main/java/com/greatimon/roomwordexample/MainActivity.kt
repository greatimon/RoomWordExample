package com.greatimon.roomwordexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    
    private lateinit var wordViewModel: WordViewModel
    private val wordListAdapter by lazy { WordListAdapter(this@MainActivity) }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(toolbar)
        
        initRecyclerView()
        initViewModel()
        setClickListener()
    }
    
    
    private fun initRecyclerView() {
        with(recyclerview) {
            adapter = wordListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
    
    private fun initViewModel() {
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { wordListAdapter.setWords(it) }
        })
    }
    
    private fun setClickListener() {
        fab.setOnClickListener {
            startActivityForResult(intentFor<NewWordActivity>(), newWordActivityRequestCode)
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        when (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            true -> {
                data?.getStringExtra(NewWordActivity.EXTRA_REPLY)?.let {
                    val word = Word(it)
                    wordViewModel.insert(word)
                }
            }
            false -> {
                toast(getString(R.string.empty_not_saved))
            }
        }
    }
    
    companion object {
        private const val newWordActivityRequestCode = 1
    }
}
