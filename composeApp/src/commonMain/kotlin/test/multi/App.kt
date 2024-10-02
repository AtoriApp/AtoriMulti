package test.multi

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TestMultiApp() {
    val navi = rememberNavController()

    TestMultiTheme {
        TestMultiNavHost(navi)
    }
}