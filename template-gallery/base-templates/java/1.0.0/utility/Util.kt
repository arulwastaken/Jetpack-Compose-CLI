package {{applicationId}}.utility

import com.google.gson.Gson


object Util {
    val gson: Gson by lazy { Gson() }

}