package com.kedar.coroutinescontactsfetching

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat

fun Context.hasPermission(permission: String): Boolean {

    return ActivityCompat.checkSelfPermission(this, permission) ==
            PackageManager.PERMISSION_GRANTED
}

fun Activity.requestPermissionWithRationale(
    permission: String,
    requestCode: Int,
    rationaleStr: String
) {
    val provideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

    if (provideRationale) {
        AlertDialog.Builder(this).apply {
            setTitle("Permission")
            setMessage(rationaleStr)
            setPositiveButton("Ok") { _, _ ->
                ActivityCompat.requestPermissions(this@requestPermissionWithRationale, arrayOf(permission), requestCode)
            }
            create()
            show()
        }
    } else {
        ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode)
    }
}