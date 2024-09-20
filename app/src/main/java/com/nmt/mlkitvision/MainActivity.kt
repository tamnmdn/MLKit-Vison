package com.nmt.mlkitvision

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback,
    AdapterView.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (!allRuntimePermissionsGranted()) {
            getRuntimePermissions()
        }

        // Set up ListView and Adapter
        val listView = findViewById<ListView>(R.id.test_activity_list_view)
        val adapter = MyArrayAdapter(this, android.R.layout.simple_list_item_2, CLASSES)
        adapter.setDescriptionIds(DESCRIPTION_IDS)
        listView.adapter = adapter
        listView.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        val clicked = CLASSES[position]
        startActivity(Intent(this, clicked))
    }

    private class MyArrayAdapter(
        private val ctx: Context,
        resource: Int,
        private val classes: Array<Class<*>>
    ) : ArrayAdapter<Class<*>>(ctx, resource, classes) {
        private var descriptionIds: IntArray? = null

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView

            if (convertView == null) {
                val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                view = inflater.inflate(android.R.layout.simple_list_item_2, null)
            }

            (view!!.findViewById<TextView>(android.R.id.text1)).text = classes[position].simpleName

            descriptionIds?.let {
                (view.findViewById<TextView>(android.R.id.text2)).setText(it[position])
            }

            return view
        }

        fun setDescriptionIds(descriptionIds: IntArray) {
            this.descriptionIds = descriptionIds
        }
    }

    private fun allRuntimePermissionsGranted(): Boolean {
        for (permission in REQUIRED_RUNTIME_PERMISSIONS) {
            permission.let {
                if (!isPermissionGranted(this, it)) {
                    return false
                }
            }
        }
        return true
    }

    private fun getRuntimePermissions() {
        val permissionsToRequest = ArrayList<String>()
        for (permission in REQUIRED_RUNTIME_PERMISSIONS) {
            permission.let {
                if (!isPermissionGranted(this, it)) {
                    permissionsToRequest.add(permission)
                }
            }
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                PERMISSION_REQUESTS
            )
        }
    }

    private fun isPermissionGranted(context: Context, permission: String): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
        ) {
            Log.i(TAG, "Permission granted: $permission")
            return true
        }
        Log.i(TAG, "Permission NOT granted: $permission")
        return false
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val PERMISSION_REQUESTS = 1

        private val REQUIRED_RUNTIME_PERMISSIONS =
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )

        private val CLASSES =
            arrayOf<Class<*>>(
                LivePreviewActivity::class.java,
                StillImageActivity::class.java,
                CameraXLivePreviewActivity::class.java,
                CameraXSourceDemoActivity::class.java
            )
        private val DESCRIPTION_IDS =
            intArrayOf(
                R.string.desc_camera_source_activity,
                R.string.desc_still_image_activity,
                R.string.desc_camerax_live_preview_activity,
                R.string.desc_cameraxsource_demo_activity
            )
    }
}