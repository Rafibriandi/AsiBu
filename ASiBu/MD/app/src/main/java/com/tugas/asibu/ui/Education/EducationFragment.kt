package com.tugas.asibu.ui.Education

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tugas.asibu.R
import com.tugas.asibu.databinding.FragmentEducationBinding


class EducationFragment : Fragment() {

private var _binding: FragmentEducationBinding? = null
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _binding = FragmentEducationBinding.inflate(layoutInflater)
    val root: View = binding.root

    val intent = Intent(activity,EducationExplanationActivity::class.java)

    //set listener for each button

    binding.cyberbullyingDefinition.setOnClickListener {
      intent.putExtra(INTENT_DATA_EDUCATION_IMAGE,R.drawable.cyberbully_icon)
    intent.putExtra(INTENT_DATA_EDUCATION_APPBAR_TEXT,resources.getText(R.string.cyberbully_definition))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT,resources.getText(R.string.cyberbully_defintition_text))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT_HYPERLINK,resources.getText(R.string.cyberbully_definition_text_hyperlink))

        startActivity(intent)
        requireActivity().overridePendingTransition(com.google.android.material.R.anim.abc_fade_in,
          androidx.appcompat.R.anim.abc_fade_out)
    }

    binding.cyberbullySign.setOnClickListener {
      intent.putExtra(INTENT_DATA_EDUCATION_IMAGE,R.drawable.cyberbully_people)
      intent.putExtra(INTENT_DATA_EDUCATION_APPBAR_TEXT,resources.getText(R.string.cyberbully_sign_header))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT,resources.getText(R.string.cyberbully_sign_text))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT_HYPERLINK,resources.getText(R.string.cyberbully_sign_text_hyperlink))
      startActivity(intent)
      requireActivity().overridePendingTransition(com.google.android.material.R.anim.abc_fade_in,
        androidx.appcompat.R.anim.abc_fade_out)
    }

    binding.cyberbulliedSign.setOnClickListener {
      intent.putExtra(INTENT_DATA_EDUCATION_IMAGE,R.drawable.cyberbullied_people)
      intent.putExtra(INTENT_DATA_EDUCATION_APPBAR_TEXT,resources.getText(R.string.cyberbullied_sign_header))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT,resources.getText(R.string.cyberbullied_sign_text))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT_HYPERLINK,resources.getText(R.string.cyberbullied_sign_text_hyperlink))
      startActivity(intent)
      requireActivity().overridePendingTransition(com.google.android.material.R.anim.abc_fade_in,
        androidx.appcompat.R.anim.abc_fade_out)
    }
    binding.cyberbullyPrevention.setOnClickListener {
      intent.putExtra(INTENT_DATA_EDUCATION_IMAGE, R.drawable.cyberbully_prevention)
      intent.putExtra(INTENT_DATA_EDUCATION_APPBAR_TEXT,resources.getText(R.string.cyberbully_prevention_header))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT,resources.getText(R.string.cyberbully_prevention_text))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT_HYPERLINK,resources.getText(R.string.cyberbully_prevention_text_hyperlink))
      startActivity(intent)
      requireActivity().overridePendingTransition(com.google.android.material.R.anim.abc_fade_in,
        androidx.appcompat.R.anim.abc_fade_out)
    }
    binding.dealingWithCyberbully.setOnClickListener {
      intent.putExtra(INTENT_DATA_EDUCATION_IMAGE,R.drawable.cyberbully_dealing)
      intent.putExtra(INTENT_DATA_EDUCATION_APPBAR_TEXT,resources.getText(R.string.dealing_with_cyberbully_header))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT,resources.getText(R.string.cyberbully_dealing_text))
      intent.putExtra(INTENT_DATA_EDUCATION_TEXT_HYPERLINK,resources.getText(R.string.cyberbully_dealing_text_hyperlink))
      startActivity(intent)
      requireActivity().overridePendingTransition(com.google.android.material.R.anim.abc_fade_in,
        androidx.appcompat.R.anim.abc_fade_out)
    }


    return root

  }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

  //variable for keep data that want to be sent
  companion object{
    val INTENT_DATA_EDUCATION_APPBAR_TEXT = "intent_data_education_appbar_text"
    val INTENT_DATA_EDUCATION_IMAGE = "intent_data_education_image"
    val INTENT_DATA_EDUCATION_TEXT = "intent_data_education_text"
    val INTENT_DATA_EDUCATION_TEXT_HYPERLINK = "intent_data_education_text_hyperlink"
  }
}