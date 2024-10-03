package app.atori.multi

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import app.atori.multi.utils.DatabaseUtils.getDb
import app.atori.multi.viewModels.XmppViewModel
import app.atori.multi.utils.Multiplatform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AtoriApp() {
    val navi = rememberNavController()
    val xmppViewModel: XmppViewModel =
        viewModel { XmppViewModel(Multiplatform.getAtoriDbBuilder().getDb().getMsgDao()) }

    TestMultiTheme {
        TestMultiNavHost(navi, xmppViewModel)
    }
}