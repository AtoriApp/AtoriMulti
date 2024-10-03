package app.atori.multi.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import app.atori.multi.components.MsgItem
import app.atori.multi.utils.ResUtils.text
import app.atori.multi.utils.ResUtils.vector
import app.atori.multi.utils.TimestampUtils.timeStr
import app.atori.multi.viewModels.XmppViewModel
import atorimulti.composeapp.generated.resources.*
import atorimulti.composeapp.generated.resources.Res
import atorimulti.composeapp.generated.resources.app_name
import atorimulti.composeapp.generated.resources.ic_atori_logo_24px
import atorimulti.composeapp.generated.resources.ic_chat_24px

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewPage(xmppViewModel: XmppViewModel) {
    val isLoggedIn by xmppViewModel.isLoggedIn.collectAsState(initial = false)
    val messages by xmppViewModel.messages.collectAsState(initial = emptyList())

    val listState = rememberLazyListState()
    val expandedFab = remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    Scaffold(
        Modifier,
        {
            TopAppBar(
                { Text(Res.string.app_name.text, maxLines = 1, overflow = TextOverflow.Ellipsis) }, Modifier,
                {
                    Box(Modifier.size(48.dp), Alignment.Center) {
                        Icon(
                            Res.drawable.ic_atori_logo_24px.vector,
                            Res.string.app_name.text, Modifier,
                            MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                {},
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceContainer)
            )
        },
        floatingActionButton = {
            Box(Modifier.padding(bottom = 8.dp, end = 8.dp)) {
                ExtendedFloatingActionButton(
                    { Text(if (isLoggedIn) Res.string.click_2_send.text else Res.string.connecting.text) },
                    {
                        Icon(
                            Res.drawable.ic_chat_24px.vector,
                            Res.string.click_2_send.text, Modifier
                        )
                    }, {
                        xmppViewModel.sendMessage("atr@conversations.im", "Hello, world!")
                    }, Modifier, expandedFab.value
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { innerPadding ->
        // 可滑动的列表
        Box(Modifier.padding(innerPadding)) {
            LazyColumn(
                Modifier, listState, PaddingValues(8.dp, 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(messages.size) {
                    MsgItem(messages[it].fromUser, "${messages[it].messageBody}\n${messages[it].timestamp.timeStr}")
                }
            }
        }
    }
}

@Composable
fun ChatPage() {

}