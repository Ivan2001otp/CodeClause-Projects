package com.ivan.srt_generator.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class IntentResultLauncher : ActivityResultContract<String,Uri?>(){
    override fun createIntent(context: Context, input: String): Intent {
        return Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "video/* image/*"
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri?{
       if(intent!!.data!=null && resultCode == Activity.RESULT_OK){
           return intent.data
       }
        return null
    }

}