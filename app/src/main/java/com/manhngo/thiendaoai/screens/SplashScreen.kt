package com.manhngo.thiendaoai.screens

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.manhngo.thiendaoai.R
import kotlinx.coroutines.delay

@OptIn(UnstableApi::class)
@Composable
fun SplashScreen(onFinished: () -> Unit) {
    val context = LocalContext.current

    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/${R.raw.loading}"))
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                this.player = player
                useController = false
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    LaunchedEffect(Unit) {
        delay(3000)
        onFinished()
    }
}
