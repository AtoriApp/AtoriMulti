package test.multi

import android.app.Application
import android.content.Context
import org.jivesoftware.smack.android.AndroidSmackInitializer

const val TAG = "Universal"

object Universal {
    lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context

        AndroidSmackInitializer.initialize(appContext)
    }
}

class AndroidPackage : Application() {
    override fun onCreate() {
        super.onCreate()

        Universal.init(applicationContext)
    }
}