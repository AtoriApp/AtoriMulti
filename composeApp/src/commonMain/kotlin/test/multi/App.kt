package test.multi

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import test.multi.utils.ResUtils.text
import test.multi.utils.ResUtils.vector

import testmulti.composeapp.generated.resources.Res
import testmulti.composeapp.generated.resources.app_name
import testmulti.composeapp.generated.resources.ic_atori_logo_24px

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    SideEffect {

    }
    TestMultiTheme {
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
                        { Text(Res.string.app_name.text) },
                        {
                            Icon(
                                Res.drawable.ic_atori_logo_24px.vector,
                                Res.string.app_name.text, Modifier
                            )
                        }, {
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

                }
            }
        }
    }
}