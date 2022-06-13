package com.tugas.asibu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tugas.asibu.databinding.ActivityResultBinding
import com.tugas.asibu.ui.home.HomeActivity
import com.tugas.asibu.ui.home.HomeFragment


class ResultActivity : AppCompatActivity() {

    private var _binding: ActivityResultBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        supportActionBar?.hide()
        _binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
       
        //variable for receiving result
        val results = intent.getFloatArrayExtra(HomeFragment.INTENT_DATA)
        
        //make sure data result already sent
        Log.d("TOXIC", results?.get(0).toString())
        Log.d("SEVERE", results?.get(1).toString())
        Log.d("OBSCENE", results?.get(2).toString())
        Log.d("threat",results?.get(3).toString())
        Log.d("INSULT",results?.get(4).toString())
        Log.d("IDENTITY",results?.get(5).toString())

        //parameter for normal word
        val threshold  = 0.1


        var largestWordMeter = results!![0]
        Log.d("largest word meter", results[0].toString())

        //finding max value of labels
        for(num in results) {
            if (num > largestWordMeter) {
                largestWordMeter = num
            }
        }

        //checking largest data result after iteration
        Log.d("largest loop ", results[0].toString())

        //set display for good word
        if(largestWordMeter < threshold){
            binding.resultCategory.text = "NORMAL"
            binding.resultDescription.text= resources.getText(R.string.cyberbully_advice_positive)
            binding.resultImage.setImageResource(R.drawable.emoji_good)
        }
        else{
              //set display for bad word
              when (largestWordMeter){
                  results[0] ->  binding.resultCategory.text = "TOXIC"
                  results[1] ->  binding.resultCategory.text = "SEVERE TOXIC"
                  results[2] ->  binding.resultCategory.text = "OBSCENE"
                  results[3] ->  binding.resultCategory.text = "THREAT"
                  results[4] ->  binding.resultCategory.text = "INSULT"
                  results[5] ->  binding.resultCategory.text = "IDENTITY HATE"
             }
            binding.resultDescription.text = resources.getText(R.string.cyberbully_advice_negative)
            binding.resultImage.setImageResource(R.drawable.emoji_bad)
        }


        binding.homeButton.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }



}