package com.rudrik.listmaker

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

//	Generic Toast message
fun String.toast(c: Context) {
    Toast.makeText(c, this, Toast.LENGTH_SHORT).show()
}

//	Generic Snackbar message
fun String.snack(v: View) {
    Snackbar.make(v, this, Snackbar.LENGTH_SHORT).show()
}

//	Generic debug logging
fun String.log(c: Context) {
    Log.d(c.javaClass.simpleName, this)
}