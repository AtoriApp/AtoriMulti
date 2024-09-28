package test.multi.utils

import android.app.Activity
import android.content.res.Configuration
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import test.multi.Universal.appContext

object CompatUtils {
    // 狠狠地把状态栏、导航栏弄美丽
    fun Activity.makeActivityBarsFit(useDarkTheme: Boolean = isSystemInDarkMode) {
        // 使内容显示在状态栏和导航栏区域
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // 设置状态栏和导航栏为透明
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        window.navigationBarColor = android.graphics.Color.TRANSPARENT

        // 控制系统栏的外观
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = !useDarkTheme
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightNavigationBars = !useDarkTheme
    }

    val isSystemInDarkMode: Boolean
        get() = appContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}