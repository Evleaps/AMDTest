package com.example.feature3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.my_core.SomeCoreClass

class MainActivity : AppCompatActivity(), SomeCoreClass {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val someField: String
        get() = "f3"
}