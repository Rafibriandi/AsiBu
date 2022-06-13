package com.tugas.asibu.ui.home

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.tugas.asibu.Classifier
import com.tugas.asibu.ResultActivity
import com.tugas.asibu.databinding.FragmentHomeBinding
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {


private var _binding: FragmentHomeBinding? = null

  private val binding get() = _binding!!

  //initiate model file
  private val MODEL_ASSETS_PATH = "model.tflite"
  //define max string input
  private val INPUT_MAXLEN = 171

    private var tfLiteInterpreter : Interpreter? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

      activity?.window?.setFlags(
          WindowManager.LayoutParams.FLAG_FULLSCREEN,
          WindowManager.LayoutParams.FLAG_FULLSCREEN
      )

    _binding = FragmentHomeBinding.inflate(inflater, container, false)
    val root: View = binding.root

      // Init the classifier.
      val classifier = Classifier( activity , "word_dict.json" , INPUT_MAXLEN )
      // Init TFLiteInterpreter
      tfLiteInterpreter = Interpreter( loadModelFile() )
      val progressDialog = ProgressDialog( activity )
      //showing message when parsing dataset
      progressDialog.setMessage( "Parsing word_dict.json ..." )
      progressDialog.setCancelable( false )
      progressDialog.show()
      classifier.processVocab( object: Classifier.VocabCallback {
          override fun onVocabProcessed() {
              // Processing done, dismiss the progressDialog.
              progressDialog.dismiss()
          }
      })

      //define button action
      binding.submitInput.setOnClickListener {
          val message = binding.userInput.text.toString().lowercase(Locale.getDefault()).trim()
          if ( !TextUtils.isEmpty( message ) ){
              // Tokenize and pad the given input text.
              val tokenizedMessage = classifier.tokenize( message )
              val paddedMessage = classifier.padSequence( tokenizedMessage )
              val results = classifySequence( paddedMessage )

              //send result to ResultActivity
              val intent = Intent(activity,ResultActivity::class.java)
              intent.putExtra(INTENT_DATA,results)
              startActivity(intent)
          }
          else{
              Toast.makeText( activity, "Please enter a message.", Toast.LENGTH_LONG).show();
          }

      }

      return root

  }

    @Throws(IOException::class)

    //open and load model file
    private fun loadModelFile(): MappedByteBuffer {
        val assetFileDescriptor = activity?.assets?.openFd(MODEL_ASSETS_PATH)
        val fileInputStream = FileInputStream(assetFileDescriptor?.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor?.startOffset
        val declaredLength = assetFileDescriptor?.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset!!, declaredLength!!)
    }

    //classify word
    private fun classifySequence (sequence : IntArray ): FloatArray {
        // Input shape -> ( 1 , INPUT_MAXLEN )
        val inputs : Array<FloatArray> = arrayOf( sequence.map { it.toFloat() }.toFloatArray() )
        // Output shape -> ( 1 , 2 ) ( as numClasses = 2 )
        val outputs : Array<FloatArray> = arrayOf( FloatArray( 6 ) )
        tfLiteInterpreter?.run( inputs , outputs )
        return outputs[0]
    }


override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //variable for send data between activity or fragment
    companion object{
        val INTENT_DATA = "intent_data"
    }
}