package com.greatimon.roomwordexample

import android.util.Log
import com.google.gson.Gson

/**
 * Created by JYN on 2020-02-21
 *
 *
 */

private const val LogTAG = "sg3"

/** -------------- Log 유틸 ----------------------------------------------- */
fun logE(message: String) {
	Log.e(LogTAG, buildLogMsg(message))
} // Level - Error

fun logW(message: String) {
	Log.w(LogTAG, buildLogMsg(message))
} // Level - Warning

fun logI(message: String) {
	Log.i(LogTAG, buildLogMsg(message))
} // Level - Information

fun logD(message: String) {
	Log.d(LogTAG, buildLogMsg(message))
} // Level - Debug

fun logV(message: String) {
	Log.v(LogTAG, buildLogMsg(message))
} // Level - Verbose

fun logJsonD(message: String) {  // Level - Debug
	Log.d(LogTAG, Gson().toJson(buildLogMsg(message)))
}

// 현재 실행중인 쓰레드의 stack 정보를 가져와서, 원하는 형식의 String 값을 리턴
private fun buildLogMsg(message: String): String {
	
	return StringBuilder().apply {
		append("[")
		// 'Thread.currentThread().stackTrace[4]'에서 가져올 수 있는 정보
		//  - getClassName()       :클래스명_ 패키지명 포함
		//  - getMethodName()      :메소드명
		//  - getFileName()        :파일명
		//  - getLineNumber()      :행 번호
		append(Thread.currentThread().stackTrace[4].fileName.replace(".kt", ""))
		append("::")
		append(Thread.currentThread().stackTrace[4].methodName)
		append("] ")
		append(message)
	}.toString()
}