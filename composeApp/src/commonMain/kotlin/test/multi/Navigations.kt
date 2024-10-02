package test.multi

import test.multi.pages.ChatPage
import test.multi.pages.OverviewPage
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

const val DestOverview = "overview"
const val DestChat = "chat"

@Composable
fun TestMultiNavHost(navi: NavHostController) {
    NavHost(navController = navi, startDestination = DestOverview) {
        composable(DestOverview) { OverviewPage( /* ... */) }
        composable(DestChat) { ChatPage( /* ... */) }
        // Add more destinations similarly.
    }
}