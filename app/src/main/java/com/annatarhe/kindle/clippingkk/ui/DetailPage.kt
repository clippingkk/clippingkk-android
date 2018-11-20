package com.annatarhe.kindle.clippingkk.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.*
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.internal.BaselineLayout
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.StaticLayout
import android.text.TextPaint
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
        val clippingID = intent.extras.getInt(DetailPage.DETAIL_KEY)
//        val clippingID = 1

        loadDetail(clippingID)
        detailCard.setOnLongClickListener {
            val bmp = makePicture()
            saveImage(bmp)
            Snackbar.make(findViewById(R.id.detailPageLayout), "pic saved", Snackbar.LENGTH_LONG).show()
            return@setOnLongClickListener true
        }
    }

    private val canvasHeight = 1920
    private val canvasWidth = 1080

    private fun makePicture(): Bitmap {
        val conf = Bitmap.Config.ARGB_4444

        val content = this.clipping!!.content

        val bmp = Bitmap.createBitmap(canvasWidth, canvasHeight, conf)
        val canvas = Canvas(bmp)
        canvas.save()

        val tp = TextPaint()
        tp.color = Color.WHITE
        tp.textSize = 80F
        tp.baselineShift = BaselineLayout.TEXT_ALIGNMENT_CENTER
        val staticLayout = StaticLayout.Builder
            .obtain(content, 0, content.length, tp, canvasWidth - 100)
            .build()

        val textHeight = staticLayout.height
        val contentStartY = (canvasHeight - textHeight) / 2

        canvas.translate(50F, contentStartY.toFloat())
        staticLayout.draw(canvas)
        canvas.restore()

        drawTitle(canvas, contentStartY, textHeight)
        drawQRCode(canvas)

        val v = View(detailPageLayout.context)
        v.draw(canvas)
        return bmp
    }

    private fun drawTitle(canvas: Canvas, contentStartY: Int, contentHeight: Int) {
        canvas.save()
        val bookName = "—— 《${this.clipping!!.title}》"

        val tp = TextPaint()
        tp.color = Color.WHITE
        tp.textSize = 80F

        val rect = Rect()
        tp.getTextBounds(bookName, 0, bookName.length, rect)
        val bookNameWidth = rect.width()
        val bookNameStartX = canvasWidth - 90 - bookNameWidth
        canvas.drawText(bookName, bookNameStartX.toFloat(), (contentStartY + contentHeight + 90).toFloat(), tp)

        canvas.restore()
    }

    private fun drawQRCode(canvas: Canvas) {
        val qrcodeOption = BitmapFactory.Options()
        qrcodeOption.inSampleSize = 4
        val qrcode = BitmapFactory.decodeResource(baseContext.resources, R.drawable.website_qrcode, qrcodeOption)

        canvas.save()
        val x = canvasWidth - 90 - 150
        val y = canvasHeight - 90 - 150

        val p = Paint()
        p.color = Color.WHITE
        canvas.drawBitmap(qrcode, x.toFloat(), y.toFloat(), p)
        canvas.restore()
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