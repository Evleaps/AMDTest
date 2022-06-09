package com.example.feature2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.my_core.SomeCoreInterface

class MainActivity : AppCompatActivity(), SomeCoreInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override val someField: String
        get() = "f2_changed"
}