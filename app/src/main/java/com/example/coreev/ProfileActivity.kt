package com.example.coreev

import android.net.Uri
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coreev.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    //view binding
    private lateinit var binding: ActivityProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //logoutBtn click, Logout the user
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        binding.switchBtn.setOnClickListener {
            val websiteUri = Uri.parse("https://www.youtube.com/")
            val intent = Intent(Intent.ACTION_VIEW, websiteUri)
            startActivity(intent)
        }
    }

    private fun checkUser() {
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser == null){
            //logout
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
        else{
            //logged in, get phone number of user
            val phone = firebaseUser.phoneNumber
            //set phone number
            binding.phoneTv.text = phone
        }
    }


}