package com.example.walmartstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_createaccount.*


class activity_createaccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createaccount)

        createAccount.setOnClickListener {
            val fname=firstN.text.toString()
            val lname=lastN.text.toString()
            val email=emailAdd.text.toString()
            val pass=passwd.text.toString()

            if(fname.isNotEmpty() && lname.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()){
                var tampUser=User(email, pass, fname,lname)
                var intent=Intent()
                intent.putExtra("user",tampUser)
                setResult(RESULT_OK,intent)
                finish()
            }
            else Toast.makeText(applicationContext,"Please input correct information",Toast.LENGTH_SHORT).show()


        }
    }
}