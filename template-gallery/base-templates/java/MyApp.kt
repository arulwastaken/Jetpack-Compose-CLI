package {{applicationId}}

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.content.Context

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }
    
    companion object {
        private var context: Context? = null
        fun getAppContext(): Context = context!!
    }
}