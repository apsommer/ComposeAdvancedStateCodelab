
package androidx.compose.samples.crane.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.samples.crane.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(
    onTimeout: () -> Unit,
    modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {

        // neither recomposition, or new onTimeout causes delay, since
        // rememberUpdatedState retains latest state, without launching long-lived lambda in LaunchedEffect
        val currentOnTimeout by rememberUpdatedState(onTimeout)
        LaunchedEffect(Unit) {
            delay(SplashWaitTime) // simulate backend network call/response
            currentOnTimeout()
        }

        Image(
            painterResource(id = R.drawable.ic_crane_drawer),
            contentDescription = null)
    }
}
