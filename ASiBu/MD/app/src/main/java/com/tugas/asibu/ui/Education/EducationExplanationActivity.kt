package com.tugas.asibu.ui.Education

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.StringRes
import com.tugas.asibu.R
import com.tugas.asibu.databinding.ActivityEducationExplanationBinding
import com.tugas.asibu.ui.home.HomeFragment


class EducationExplanationActivity : AppCompatActivity() {

    private var _binding : ActivityEducationExplanationBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_education_explanation)
        _binding = ActivityEducationExplanationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val bundle = intent.extras

        //variables for receiving data intent
        val dataImage = bundle?.getInt(EducationFragment.INTENT_DATA_EDUCATION_IMAGE)
        val dataAppBarText  = intent.getStringExtra(EducationFragment.INTENT_DATA_EDUCATION_APPBAR_TEXT)
        val dataText = intent.getStringExtra(EducationFragment.INTENT_DATA_EDUCATION_TEXT)
        val dataTextHyperLink = intent.getCharSequenceExtra(EducationFragment.INTENT_DATA_EDUCATION_TEXT_HYPERLINK)





       binding.appbarText.text = dataAppBarText
        if (dataImage != null) {
            binding.educationExplanationImage.setImageResource(dataImage)
        }

        //displaying result on layout
        binding.educationExplanationText.text = dataText
        binding.educationExplanationHyperlink.text = dataTextHyperLink
        binding.educationExplanationHyperlink.movementMethod = LinkMovementMethod.getInstance()

    }

}