package com.example.assignment.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment.databinding.ActivityAssignmentBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AssignmentActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Any>
    lateinit var mActivityAssignmentBinding: ActivityAssignmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mActivityAssignmentBinding = ActivityAssignmentBinding.inflate(layoutInflater)
        setContentView(mActivityAssignmentBinding.root)
        setClick()
    }

    private fun setClick() {
        mActivityAssignmentBinding.button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        mActivityAssignmentBinding.button2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}