package com.example.courotinesdemo


import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    var customProgressDialog : Dialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
          val button : Button = findViewById(R.id.button)
        button.setOnClickListener {
            showProgressDialog()
         lifecycleScope.launch{
             execute("Task Completed")
         }

        }


    }
    private suspend fun execute(message : String){

        withContext(Dispatchers.IO){
            for (i in 1..90000){
                Log.d("MainActivity","$i")
            }
        }

        runOnUiThread {
            stopProgressDialog()
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        }

    }

    private fun stopProgressDialog(){
        if(customProgressDialog!=null){
            customProgressDialog?.dismiss()
            customProgressDialog = null
        }

    }

    private fun showProgressDialog(){
       customProgressDialog = Dialog(this)

        customProgressDialog?.setContentView(R.layout.progress_bar)

        customProgressDialog?.show()

    }

}