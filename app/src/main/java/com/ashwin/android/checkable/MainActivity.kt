package com.ashwin.android.checkable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ashwin.android.checkable.imagebutton.ImageButtonActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openImageButtonActivity(view: View) {
        startActivity(Intent(this, ImageButtonActivity::class.java))
    }
}
