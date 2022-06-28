package {{applicationId}}.manager

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import {{applicationId}}.utility.Constants


object PrefManager {

    private const val prefAppFirstStart: String = "pref_app_first_start"



    private val preference: SharedPreferences by lazy {
        MyApp.getAppContext().getSharedPreferences(
            Constants.PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }

      @SuppressLint("ApplySharedPref")
    private fun putString(prefName: String, value: String?) {
        preference.edit().apply {
            putString(prefName, value)
            commit()
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun putInt(prefName: String, value: Int?, async: Boolean = false) {
        preference.edit().run {
            putInt(prefName, value)
            if (async)
                apply()
            else
                commit()
        }
    }

    @SuppressLint("ApplySharedPref")
    private fun putBoolean(prefName: String, value: Boolean?) {
        preference.edit().run {
            putBoolean(prefName, value)
            apply()
        }
    }

    var appInitStarted: Boolean?
        get() = preference.getBoolean(prefAppFirstStart, false)
        set(value) = putBoolean(prefAppFirstStart, value)

}