package com.siddharthpathania.firewatchparallaxlw

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SeekBarPreference
import com.marcoscg.licenser.Library
import com.marcoscg.licenser.License
import com.marcoscg.licenser.LicenserDialog


class SettingsActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    class SettingsFragment : PreferenceFragmentCompat() {
        private lateinit var lyr7:SeekBarPreference
        private lateinit var lyr6:SeekBarPreference
        private lateinit var lyr5:SeekBarPreference
        private lateinit var lyr4:SeekBarPreference
        private lateinit var lyr3:SeekBarPreference
        private lateinit var lyr2:SeekBarPreference
        private lateinit var lyr1:SeekBarPreference
        private lateinit var sclFactor:SeekBarPreference


        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            findPreference<Preference>("license")?.setOnPreferenceClickListener { return@setOnPreferenceClickListener showPrefs();
            }
            findPreference<Preference>("set_default")?.setOnPreferenceClickListener { return@setOnPreferenceClickListener setDefault();
            }
            lyr7 = findPreference<Preference>("L7") as SeekBarPreference
            lyr6 = findPreference<Preference>("L6") as SeekBarPreference
            lyr5 = findPreference<Preference>("L5") as SeekBarPreference
            lyr4 = findPreference<Preference>("L4") as SeekBarPreference
            lyr3 = findPreference<Preference>("L3") as SeekBarPreference
            lyr2 = findPreference<Preference>("L2") as SeekBarPreference
            lyr1 = findPreference<Preference>("L1") as SeekBarPreference
            sclFactor = findPreference<Preference>("ScaleFactor") as SeekBarPreference

        }

        private fun showPrefs (): Boolean {
            LicenserDialog(this.context)
                .setTitle("Licenses")
                //.setCustomNoticeTitle("Notices for files:")
                .setLibrary(
                    Library(
                        "FireWatch LiveWallpaper",
                        "https://github.com/sidpathania/FireWatchLW",
                        License.GNU3
                    )
                )
                .setLibrary(
                    Library(
                        "AndroidX Support Libraries",
                        "https://developer.android.com/jetpack/androidx",
                        License.APACHE2
                    )
                )
                .setLibrary(Library("Kotlin stdlib", "https://kotlinlang.org", License.APACHE2))
                .setLibrary(
                    Library(
                        "Color Picker",
                        "https://github.com/jaredrummler/ColorPicker",
                        License.APACHE2
                    )
                )
                .setLibrary(
                    Library(
                        "Licenser",
                        "https://github.com/marcoscgdev/Licenser",
                        License.MIT
                    )
                )
                .setPositiveButton(android.R.string.ok,
                    DialogInterface.OnClickListener { _, _ -> })
                .show()
            return true
        }

        private fun setDefault (): Boolean {
            lyr7.value = 100
            lyr6.value = 85
            lyr5.value = 70
            lyr4.value = 55
            lyr3.value = 40
            lyr2.value = 25
            lyr1.value = 10
            sclFactor.value = 85
            return true
        }
    }

}
