package test.multi

import android.app.Application
import android.content.Context

object Universal {
    lateinit var appContext: Context

    fun init(context: Context) {
        appContext = context
    }
}

class TestMultiApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Universal.init(applicationContext)
    }
}