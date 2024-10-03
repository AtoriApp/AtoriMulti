package app.atori.multi

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import app.atori.multi.utils.ResUtils.text
import org.jetbrains.compose.resources.painterResource
import atorimulti.composeapp.generated.resources.Res
import atorimulti.composeapp.generated.resources.app_name
import atorimulti.composeapp.generated.resources.ic_atori_icon
import atorimulti.composeapp.generated.resources.ic_atori_icon_dark

fun main() = application {
    Window(
        icon = if (isSystemInDarkTheme()) painterResource(Res.drawable.ic_atori_icon) else painterResource(Res.drawable.ic_atori_icon_dark),
        onCloseRequest = ::exitApplication,
        title = Res.string.app_name.text,
    ) {
        AtoriApp()
    }
}