package com.example.newpets

import Model.Ayam
import Model.Hewan
import Model.Kambing
import Model.Sapi
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.newpets.databinding.ActivityAddBinding
import database.Globalvar
import android.widget.Toast

import android.widget.RadioButton
import androidx.core.view.get
import android.R

import android.widget.RadioGroup





class AddActivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityAddBinding
    private lateinit var hewan: Hewan
    var pcc = -1
    var img: String = ""


    private val GetResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val uri = it.data?.data
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    if (uri != null) {
                        baseContext.getContentResolver().takePersistableUriPermission(
                            uri,
                            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        )
                    }
                }
                viewBind.imageView2.setImageURI(uri)
                img = uri.toString()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityAddBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        getintent()
        listener()
    }

    private fun getintent() {
        pcc = intent.getIntExtra("position", -1)
        if (pcc != -1) {
            val hewan = Globalvar.listDatahewan[pcc]
            viewBind.toolbar2.title = "Ganti data hewan"
            viewBind.Addmovie.text = "Edit"
            viewBind.imageView2.setImageURI(Uri.parse(Globalvar.listDatahewan[pcc].imageUri))
            viewBind.Rating.editText?.setText(hewan.umur.toString())
            viewBind.Title.editText?.setText(hewan.nama)
            when(hewan?.jenis) {
                "Ayam" -> viewBind.radioButton.setChecked(true)
                "Sapi" -> viewBind.radioButton2.setChecked(true)
                "Kambing" -> viewBind.radioButton3.setChecked(true)
            }
//            if (hewan.jenis == "Ayam") {
//                viewBind.radioButton.isChecked = true;
//            } else if (hewan.jenis == "Sapi") {
//                viewBind.radioButton2.isChecked = true;
//            } else if (hewan.jenis == "Kambing") {
//                viewBind.radioButton3.isChecked = true;
//            }

        }
    }


    private fun listener() {
        viewBind.imageView2.setOnClickListener {
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.Addmovie.setOnClickListener {

            if (viewBind.radioGroup.checkedRadioButtonId == -1) {
                var nama = viewBind.Title.editText?.text.toString().trim()
                var umur = 0
                var jenis = ""
                hewan = Ayam(nama, jenis, umur)
                checker()
            } else {
                var nama = viewBind.Title.editText?.text.toString().trim()
                var umur = 0
                var jenis =
                    findViewById<RadioButton>(viewBind.radioGroup.checkedRadioButtonId).text.toString()
                if (jenis == "Ayam") {
                    hewan = Ayam(nama, jenis, umur)
                    checker()
                } else if (jenis == "Sapi") {
                    hewan = Sapi(nama, jenis, umur)
                    checker()
                } else {
                    hewan = Kambing(nama, jenis, umur)
                    checker()
                }

            }
        }


        viewBind.toolbar2.getChildAt(1).setOnClickListener {
            finish()
        }
    }

    private fun checker() {
        var isCompleted: Boolean = true
        hewan.imageUri = img.toString()
            if (hewan.nama!!.isEmpty()) {
                viewBind.Title.error = "Nama tidak bisa kosong"
                isCompleted = false
            } else {
                viewBind.Title.error = ""
            }
        if(hewan.jenis!!.isEmpty()){
            viewBind.radioButton.error = "?"
            viewBind.radioButton2.error = "?"
            viewBind.radioButton3.error = "?"
            isCompleted = false
        }
            if (viewBind.Rating.editText?.text.toString()
                    .isEmpty() || viewBind.Rating.editText?.text.toString().toInt() < 0
            ) {
                viewBind.Rating.error = "Umur tidak bisa kosong atau 0"
                isCompleted = false
            }
            if (isCompleted == true) {
                if (pcc == -1) {
                    hewan.umur = viewBind.Rating.editText?.text.toString().toInt()
                    Globalvar.listDatahewan.add(hewan)

                } else {
                    hewan.umur = viewBind.Rating.editText?.text.toString().toInt()
                    Globalvar.listDatahewan[pcc] = hewan
                }
                finish()
            }
        }
    }
