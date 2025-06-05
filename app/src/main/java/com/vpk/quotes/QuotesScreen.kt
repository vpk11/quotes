package com.vpk.quotes

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.material.icons.filled.Info

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesScreen(quotes: List<Quote>) {
    val localClipboardManager = LocalClipboardManager.current
    val localContext = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                actions = {
                    IconButton(onClick = {
                        // Intent to start AboutActivity
                        val intent = Intent(localContext, AboutActivity::class.java)
                        localContext.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = stringResource(R.string.about)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(quotes) { quote ->
                QuoteCard(
                    quote = quote,
                    onQuoteClick = { clickedQuote ->
                        copyQuoteToClipboardHelper(localClipboardManager, localContext, clickedQuote)
                    }
                )
            }
        }
    }
}

fun copyQuoteToClipboardHelper(
    clipboardManager: androidx.compose.ui.platform.ClipboardManager,
    context: Context,
    quote: Quote
) {
    clipboardManager.setText(AnnotatedString("\"${quote.text}\" - ${quote.author}"))
    Toast.makeText(context, "Quote copied!", Toast.LENGTH_SHORT).show()
}