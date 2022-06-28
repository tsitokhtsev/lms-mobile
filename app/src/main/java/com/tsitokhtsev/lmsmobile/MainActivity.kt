package com.tsitokhtsev.lmsmobile

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.tsitokhtsev.lmsmobile.databinding.ActivityMainBinding
import com.tsitokhtsev.lmsmobile.model.SubjectsResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val jsonString = intent.getStringExtra("studentData")
        val gson = GsonBuilder().create()
        val response = gson.fromJson(jsonString, SubjectsResponse::class.java)

        binding.subjectList.layoutManager = LinearLayoutManager(this)
        binding.subjectList.adapter = SubjectListAdapter(response)
    }
}