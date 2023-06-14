package com.ivan.srt_generator

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ivan.srt_generator.Utils.HelperRoutines
import com.ivan.srt_generator.Utils.IntentResultLauncher
import com.ivan.srt_generator.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val pickVideoLauncher = registerForActivityResult(IntentResultLauncher()){ videoUri->
        var filename : String ?= null
        val context = this@MainActivity
        val contentResolver = this.contentResolver
        if(videoUri != null){
                    filename = HelperRoutines().fileNameFromURI(contentResolver,videoUri)
                    binding.fileNameTv.text = filename
        }else{
            Toast.makeText(this,"Please ,Pick only videos file.",Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.selectFileBtn.setOnClickListener { view->
            Log.d("tag", "onCreate: browse clicked")
            pickVideoLauncher.launch("video/* image/*")
        }


    }

}