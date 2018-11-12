package com.annatarhe.kindle.clippingkk.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.annatarhe.kindle.clippingkk.R
import com.annatarhe.kindle.clippingkk.model.ClippingAPI
import kotlinx.android.synthetic.main.activity_detail.*


class DetailPage : AppCompatActivity() {
    companion object {
        val DETAIL_KEY = "DETAIL"
    }

    var clipping: ClippingAPI.ClippingItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        //val clippingID = intent.extras.getInt(DetailPage.DETAIL_KEY)
        val clippingID = 1

        loadDetail(clippingID)
        detailCard.setOnLongClickListener {
            val bmp = makePicture()
            saveImage(bmp)
            Snackbar.make(findViewById(R.id.detailPageLayout), "pic saved", Snackbar.LENGTH_LONG).show()
            return@setOnLongClickListener true
        }
    }

    private fun makePicture(): Bitmap {
        val conf = Bitmap.Config.ARGB_8888
        val bmp = Bitmap.createBitmap(1080, 1920, conf)
        val canvas = Canvas(bmp)
        canvas.save()
        canvas.restore()

        val v = View(detailPageLayout.context)
        v.draw(canvas)
        return bmp
    }

    fun saveImage(bmp: Bitmap) {
        val hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
            return
        }
        Log.i("detail", bmp.toString())
        MediaStore.Images.Media.insertImage(contentResolver, bmp, this.clipping?.title, this.clipping?.content)
    }

    private fun loadDetail(id: Int) {
        ClippingAPI().fetchDetail(id, { clippingItem ->
            this.clipping = clippingItem
            detailBookName.text = clippingItem.title
            detailBookContent.text = clippingItem.content
            Log.i("detail", clippingItem.toString())
        }, { err ->
            Log.i("detail", err)
            Snackbar.make(findViewById(R.id.detailPageLayout), err, Snackbar.LENGTH_SHORT).show()
        })
    }

    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }
}