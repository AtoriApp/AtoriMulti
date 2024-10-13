package app.atori.multi

import app.atori.multi.pages.ChatPage
import app.atori.multi.pages.OverviewPage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import app.atori.multi.pages.LoginPage
import app.atori.multi.utils.NavigatorUtils.naviIfNotHere
import app.atori.multi.viewModels.XmppViewModel

const val DestOverview = "overview"
const val DestChat = "chat"
const val DestLogin = "login"

@Composable
fun AtoriMultiNavHost(navi: NavHostController, xmppViewModel: XmppViewModel) {
    val noAccount by xmppViewModel.noAccount.collectAsState(initial = false)

    LaunchedEffect(navi, noAccount) {
        if (noAccount) navi.naviIfNotHere(DestLogin)
        else navi.naviIfNotHere(DestOverview)
    }


    NavHost(navController = navi, startDestination = DestOverview) {
        composable(DestOverview) { OverviewPage(xmppViewModel) }
        composable(DestLogin) { LoginPage(xmppViewModel /* ... */) }
        // Add more destinations similarly.
    }
}