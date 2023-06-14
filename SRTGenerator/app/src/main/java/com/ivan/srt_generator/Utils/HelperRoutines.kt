package com.ivan.srt_generator.Utils

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.annotation.WorkerThread

class HelperRoutines() {


   fun fileNameFromURI(contentResolver : ContentResolver, uri : Uri):String?{

        var fileName:String ?= null
        val cursor: Cursor? = contentResolver.query(uri,null,null,null,null)

        cursor?.use {
            if(it.moveToFirst()){
                val displayColumnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                Log.d("tag", "fileNameFromURI: $displayColumnIndex")
                Log.d("tag", "fileName: ${it.getString(displayColumnIndex)}")
                if(displayColumnIndex!=-1){
                    fileName = it.getString(displayColumnIndex)
                }
            }
        }
        cursor?.close()
    return fileName
    }
}