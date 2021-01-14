package com.singhez.kidsdrawingapp

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*
import kotlinx.android.synthetic.main.dialog_color_chooser.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.File.separator
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawing_view.setSizeForBrush(10.toFloat())

        ib_brush.setOnClickListener{
            showBrushSizeChooserDialog()

        }

        ib_color.setOnClickListener{
            showColorChooserDialog()
        }

        ib_gallery.setOnClickListener{
            if (isReadStorageAllowed()){

                val pickPhotoIntent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )

                startActivityForResult(pickPhotoIntent, GALLERY)

            }else{
                requestStoragePermission()
            }
        }

        ib_undo.setOnClickListener{
            drawing_view.onClickUndo()
        }

        ib_redo.setOnClickListener{
            drawing_view.onClickRedo()
        }

        ib_save.setOnClickListener{
            if (isReadStorageAllowed()){
                BitmapAsyncTask(getBitmapFromView(fl_drawing_view_container)).execute()
            }else{
                requestStoragePermission()

            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == GALLERY){
                try{
                    if (data!!.data != null){
                        iv_background.visibility = View.VISIBLE
                        iv_background.setImageURI(data.data)
                    }else{
                        Toast.makeText(
                            this@MainActivity,
                            "Error in parsing the image or its corrupted",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }

    }

    private fun showBrushSizeChooserDialog(){
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Please Choose Brush Size:")
        val smallBtn = brushDialog.ib_small_brush
        smallBtn.setOnClickListener{
            drawing_view.setSizeForBrush(5.toFloat())
            ib_brush.setImageResource(R.drawable.small)
            brushDialog.dismiss()
        }
        val small2Btn = brushDialog.ib_small2_brush
        small2Btn.setOnClickListener{
            drawing_view.setSizeForBrush(10.toFloat())
            ib_brush.setImageResource(R.drawable.small2)
            brushDialog.dismiss()
        }
        val mediumBtn = brushDialog.ib_medium_brush
        mediumBtn.setOnClickListener{
            drawing_view.setSizeForBrush(15.toFloat())
            ib_brush.setImageResource(R.drawable.medium)
            brushDialog.dismiss()
        }
        val medium2Btn = brushDialog.ib_medium2_brush
        medium2Btn.setOnClickListener{
            drawing_view.setSizeForBrush(20.toFloat())
            ib_brush.setImageResource(R.drawable.medium2)
            brushDialog.dismiss()
        }
        val largeBtn = brushDialog.ib_large_brush
        largeBtn.setOnClickListener{
            drawing_view.setSizeForBrush(25.toFloat())
            ib_brush.setImageResource(R.drawable.large)
            brushDialog.dismiss()
        }
        val large2Btn = brushDialog.ib_large2_brush
        large2Btn.setOnClickListener{
            drawing_view.setSizeForBrush(30.toFloat())
            ib_brush.setImageResource(R.drawable.large2)
            brushDialog.dismiss()
        }

        brushDialog.show()
    }

    private fun showColorChooserDialog(){
        val colorDialog = Dialog(this)
        colorDialog.setContentView(R.layout.dialog_color_chooser)
        colorDialog.setTitle("Please Choose Your Color:")
        val black = colorDialog.black
        black.setOnClickListener{
            drawing_view.setColor(black.tag.toString())
            ib_color.setBackgroundResource(R.color.black)
            colorDialog.dismiss()
        }
        val grey = colorDialog.grey
        grey.setOnClickListener{
            drawing_view.setColor(grey.tag.toString())
            ib_color.setBackgroundResource(R.color.grey)
            colorDialog.dismiss()
        }
        val white = colorDialog.white
        white.setOnClickListener{
            drawing_view.setColor(white.tag.toString())
            ib_color.setBackgroundResource(R.color.white)
            colorDialog.dismiss()
        }
        val col2 = colorDialog.col2
        col2.setOnClickListener{
            drawing_view.setColor(col2.tag.toString())
            ib_color.setBackgroundResource(R.color.col2)
            colorDialog.dismiss()
        }
        val col3 = colorDialog.col3
        col3.setOnClickListener{
            drawing_view.setColor(col3.tag.toString())
            ib_color.setBackgroundResource(R.color.col3)
            colorDialog.dismiss()
        }

        val col4 = colorDialog.col4
        col4.setOnClickListener{
            drawing_view.setColor(col4.tag.toString())
            ib_color.setBackgroundResource(R.color.col4)
            colorDialog.dismiss()
        }

        val col5 = colorDialog.col5
        col5.setOnClickListener{
            drawing_view.setColor(col5.tag.toString())
            ib_color.setBackgroundResource(R.color.col5)
            colorDialog.dismiss()
        }

        val col6 = colorDialog.col6
        col6.setOnClickListener{
            drawing_view.setColor(col6.tag.toString())
            ib_color.setBackgroundResource(R.color.col6)
            colorDialog.dismiss()
        }

        val col7 = colorDialog.col7
        col7.setOnClickListener{
            drawing_view.setColor(col7.tag.toString())
            ib_color.setBackgroundResource(R.color.col7)
            colorDialog.dismiss()
        }

        val col8 = colorDialog.col8
        col8.setOnClickListener{
            drawing_view.setColor(col8.tag.toString())
            ib_color.setBackgroundResource(R.color.col8)
            colorDialog.dismiss()
        }

        val col9 = colorDialog.col9
        col9.setOnClickListener{
            drawing_view.setColor(col9.tag.toString())
            ib_color.setBackgroundResource(R.color.col9)
            colorDialog.dismiss()
        }

        val col10 = colorDialog.col10
        col10.setOnClickListener{
            drawing_view.setColor(col10.tag.toString())
            ib_color.setBackgroundResource(R.color.col10)
            colorDialog.dismiss()
        }

        val col11 = colorDialog.col11
        col11.setOnClickListener{
            drawing_view.setColor(col11.tag.toString())
            ib_color.setBackgroundResource(R.color.col11)
            colorDialog.dismiss()
        }

        val col12 = colorDialog.col12
        col12.setOnClickListener{
            drawing_view.setColor(col12.tag.toString())
            ib_color.setBackgroundResource(R.color.col12)
            colorDialog.dismiss()
        }

        val col13 = colorDialog.col13
        col13.setOnClickListener{
            drawing_view.setColor(col13.tag.toString())
            ib_color.setBackgroundResource(R.color.col13)
            colorDialog.dismiss()
        }

        val col14 = colorDialog.col14
        col14.setOnClickListener{
            drawing_view.setColor(col14.tag.toString())
            ib_color.setBackgroundResource(R.color.col14)
            colorDialog.dismiss()
        }

        val col15 = colorDialog.col15
        col15.setOnClickListener{
            drawing_view.setColor(col15.tag.toString())
            ib_color.setBackgroundResource(R.color.col15)
            colorDialog.dismiss()
        }

        val col16 = colorDialog.col16
        col16.setOnClickListener{
            drawing_view.setColor(col16.tag.toString())
            ib_color.setBackgroundResource(R.color.col16)
            colorDialog.dismiss()
        }

        val col17 = colorDialog.col17
        col17.setOnClickListener{
            drawing_view.setColor(col17.tag.toString())
            ib_color.setBackgroundResource(R.color.col17)
            colorDialog.dismiss()
        }

        val col18 = colorDialog.col18
        col18.setOnClickListener{
            drawing_view.setColor(col18.tag.toString())
            ib_color.setBackgroundResource(R.color.col18)
            colorDialog.dismiss()
        }

        val bg = colorDialog.bg
        bg.setOnClickListener{
            drawing_view.setColor(bg.tag.toString())
            ib_color.setBackgroundResource(R.color.bg)
            colorDialog.dismiss()
        }

        colorDialog.show()

    }

    private fun requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).toString()
            )){
            Toast.makeText(
                this, "Please allow permission to add a Background Image",
                Toast.LENGTH_LONG
            ).show()
        }
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), STORAGE_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PERMISSION_CODE){
        if (grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(
                this,
                "Permission granted",
                Toast.LENGTH_LONG
            ).show()
        }else{
            Toast.makeText(
                this,
                "Oops You Denied The Permission",
                Toast.LENGTH_LONG
            ).show()
        }

        }
    }

    private fun isReadStorageAllowed() : Boolean{
        val result = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun getBitmapFromView(view: View) : Bitmap{
        val returnedBitmap = Bitmap.createBitmap(
            view.width,
            view.height, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if(bgDrawable != null){
            bgDrawable.draw(canvas)
        }else{
            canvas.drawColor(Color.WHITE)
        }

        view.draw(canvas)

        return returnedBitmap

    }

    open inner class BitmapAsyncTask(val mBitmap: Bitmap) :
        AsyncTask<Any, Void, String> () {

        lateinit var mProgressDialog : Dialog
        override fun onPreExecute() {
            super.onPreExecute()
            showProgressDialog()
        }


        override fun doInBackground(vararg params: Any?): String {

            var result = ""

            if (mBitmap != null){
                try {
                    val bytes = ByteArrayOutputStream()
                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                    val f = File(externalCacheDir!!.absoluteFile.toString() + separator
                                + "KidsDrawingApp_"
                                + System.currentTimeMillis() / 1000 + ".jpeg"
                    )

                    val fos = FileOutputStream(f)
                    fos.write(bytes.toByteArray())
                    fos.close()
                    result = f.absolutePath

                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
            return result

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            cancelProgressDialog()
            if (!result!!.isEmpty()){
                Toast.makeText(
                    this@MainActivity,
                    "your art saved successfully on $result",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                Toast.makeText(
                    this@MainActivity,
                    "Something went wrong while saving your art",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
        private fun showProgressDialog(){
            mProgressDialog = Dialog(this@MainActivity)
            mProgressDialog.setContentView(R.layout.dialog_custom_progress_bar)
            mProgressDialog.show()
        }
        private fun cancelProgressDialog(){
            mProgressDialog.dismiss()
        }

    }


    companion object{
       private const val STORAGE_PERMISSION_CODE = 1
       private const val GALLERY = 2
    }

}