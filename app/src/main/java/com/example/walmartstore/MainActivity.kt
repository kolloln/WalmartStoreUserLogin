package com.example.walmartstore

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_createaccount.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val users=ArrayList<User>()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            if(result.resultCode == Activity.RESULT_OK){
                var dataIntent=result.data?.getSerializableExtra("user")
                var user:User=dataIntent as User
                users.add(user)
            }

        }

        var user1=User("kallol.shekhor@miu.edu","123456","Kallol","Shekhor")
        var user2=User("kolloln@gmail.com","123456","Shekhor","Chakraborty")
        var user3=User("smith_c@yahoo.com","123456","Smith","Chakraborty")
        var user4=User("shreyan_c@gmail.com","123456","Shreyan","Chakraborty")
        var user5=User("shekhor.kallol@gmail.com","123456","Shekhor","Chakraborty")

        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user5)
        signBtn.setOnClickListener {
            val email=emailInpput.text.toString()
            val password=passInput.text.toString()

            for (i in users){
                if (email==i.username && password==i.password){
                    var intent = Intent(this,activity_Shopping::class.java)
                    intent.putExtra("1",email)
                    startActivity(intent);
                    Toast.makeText(applicationContext,"Account Created Successfully",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

            }
            Toast.makeText(applicationContext,"User Not found",Toast.LENGTH_SHORT).show()
        }

        button2.setOnClickListener(){
            var intent_create=Intent(this,activity_createaccount::class.java)
              resultContracts.launch(intent_create)
        }

        forgotView.setOnClickListener(){
            var email=emailInpput.text.toString()
            for (i in users){
                if(email==i.username){
                    var setIntent=Intent(Intent.ACTION_SENDTO, Uri.parse("mailto"))
                    setIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    setIntent.putExtra(Intent.EXTRA_SUBJECT,"Recovered Password")
                    setIntent.putExtra(Intent.EXTRA_TEXT,"Password is: ${i.password}")
                    startActivity(Intent.createChooser(setIntent,"Sending...."))
                    return@setOnClickListener

                }
            }
            Toast.makeText(applicationContext,"User not found",Toast.LENGTH_SHORT).show()
        }


    }

}