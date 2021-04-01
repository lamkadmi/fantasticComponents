package com.fantastic.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fantastic.component.R
import com.fantastic.component.toast.CustomToast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //MyToast.success(this, "Success")?.show()
        CustomToast.error(this, "Error", 3000)?.show()
    }
}