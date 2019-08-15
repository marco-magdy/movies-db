package com.movies.view

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 *Created by marco on 2019-08-09
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun initViewModel()
    abstract fun initObservables()

    protected fun showMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}