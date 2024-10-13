package app.atori.multi

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import app.atori.multi.utils.DatabaseUtils.buildDb
import app.atori.multi.viewModels.XmppViewModel
import app.atori.multi.utils.MultiplatformIO

@Composable
fun AtoriApp() {
    val navi = rememberNavController()
    val xmppViewModel: XmppViewModel =
        viewModel { XmppViewModel(MultiplatformIO.getAtoriDbBuilder().buildDb()) }

    AtoriMultiTheme {
        AtoriMultiNavHost(navi, xmppViewModel)
    }
}