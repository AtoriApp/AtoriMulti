package test.multi

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import testmulti.composeapp.generated.resources.Res
import testmulti.composeapp.generated.resources.ic_atori_icon
import testmulti.composeapp.generated.resources.ic_atori_icon_dark

fun main() = application {
    Window(
        icon = if (isSystemInDarkTheme()) painterResource(Res.drawable.ic_atori_icon) else painterResource(Res.drawable.ic_atori_icon_dark),
        onCloseRequest = ::exitApplication,
        title = "Test Multi",
    ) {
        TestMultiApp()
    }
}