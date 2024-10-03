package app.atori.multi

import app.atori.multi.pages.ChatPage
import app.atori.multi.pages.OverviewPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.atori.multi.viewModels.XmppViewModel

const val DestOverview = "overview"
const val DestChat = "chat"

@Composable
fun TestMultiNavHost(navi: NavHostController, xmppViewModel: XmppViewModel) {
    NavHost(navController = navi, startDestination = DestOverview) {
        composable(DestOverview) { OverviewPage(xmppViewModel) }
        composable(DestChat) { ChatPage( /* ... */) }
        // Add more destinations similarly.
    }
}