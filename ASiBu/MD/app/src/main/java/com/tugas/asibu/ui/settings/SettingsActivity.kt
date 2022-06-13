package com.tugas.asibu.ui.settings

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.tugas.asibu.ProfileActivity
import com.tugas.asibu.R

class SettingsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }


    }

    class SettingsFragment : PreferenceFragmentCompat() {

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)


                val preferenceSwitch = findPreference<SwitchPreferenceCompat>("dark_mode")
                val preferenceButton = findPreference<Preference>("about_us")

                //set switch button to dark mode and reverse it
                 preferenceSwitch!!.onPreferenceChangeListener = Preference.OnPreferenceChangeListener{
                     pref,newValue ->

                     if(pref is SwitchPreferenceCompat){
                         val value = newValue as Boolean
                         if(value) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                         else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                     }
                     true
                 }

            //set button for move to another layout
            preferenceButton?.setOnPreferenceClickListener {
                val intent = Intent(context,ProfileActivity::class.java)
                startActivity(intent)
                true
            }

        }
    }
}