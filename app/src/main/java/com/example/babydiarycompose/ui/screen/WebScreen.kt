package com.example.babydiarycompose.ui.screen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@Composable
 fun MyWebComposable(url: String) {
    AndroidView(factory = { ctx ->
        WebView(ctx)
    }, update = {
        it.webViewClient = WebViewClient()
        it.loadUrl(url)
    }, modifier = Modifier.fillMaxSize()
    )
}