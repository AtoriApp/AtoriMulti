package test.multi

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.resources.painterResource
import test.multi.utils.ResUtils.vector
import testmulti.composeapp.generated.resources.Res
import testmulti.composeapp.generated.resources.ic_atori_logo_24px

fun main() = application {
    Window(
        icon = painterResource(Res.drawable.ic_atori_logo_24px),
        onCloseRequest = ::exitApplication,
        title = "TestMulti",
    ) {
        App()
    }
}