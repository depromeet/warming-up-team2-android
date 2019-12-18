package com.depromeet.android.childcare.util

import android.Manifest
import android.content.Context
import android.widget.Toast
import com.depromeet.android.childcare.R
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission


object PermissionUtil {

    fun getPermission(context: Context, success: () -> Unit,failed: () -> Unit) {
        TedPermission.with(context)
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {
                    run(success)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(context, context.getString(R.string.add_item_msg_no_pms), Toast.LENGTH_SHORT).show()
                    run(failed)
                }
            })
            .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE)
            .check()
    }
}