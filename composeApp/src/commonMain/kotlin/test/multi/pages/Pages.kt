package test.multi.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.multi.components.MsgItem
import test.multi.services.XMPPService
import test.multi.utils.ResUtils.text
import test.multi.utils.ResUtils.vector
import testmulti.composeapp.generated.resources.*
import testmulti.composeapp.generated.resources.Res
import testmulti.composeapp.generated.resources.app_name
import testmulti.composeapp.generated.resources.ic_atori_logo_24px
import testmulti.composeapp.generated.resources.ic_chat_24px

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewPage() {
    val service = mutableStateOf(XMPPService())
    val messages = mutableStateListOf<Pair<String, String>>()

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            service.value.connect("ezc", "j1N2i2U4evER@co", "suchat.org")
            service.value.addIncomingMessageListener { jid, message, _ ->
                val m = "${message.body}\n${message.stanzaId}"
                println("Received message from $jid:\n$m")
                messages.add(Pair(jid.toString(), m))
            }
        }
    }


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
                        { Text(Res.string.click_2_send.text) },
                        {
                            Icon(
                                Res.drawable.ic_chat_24px.vector,
                                Res.string.click_2_send.text, Modifier
                            )
                        }, {
                            service.value.sendMessage("atr@conversations.im", "Hello, world!")
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
                        MsgItem(messages[it].first, messages[it].second)
                    }
                }
            }
        }
}

@Composable
fun ChatPage() {

}