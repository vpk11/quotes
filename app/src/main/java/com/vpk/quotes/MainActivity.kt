package com.vpk.quotes

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var loadedQuotes by remember { mutableStateOf<List<Quote>>(emptyList()) }
            // Use LaunchedEffect for side effects like loading data
            LaunchedEffect(Unit) { // Keyed on Unit to run once
                // Perform file I/O on a background thread
                loadedQuotes = withContext(Dispatchers.IO) {
                    loadQuotesFromAssets(applicationContext)
                }
            }

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (loadedQuotes.isNotEmpty()) {
                        QuotesScreen(quotes = loadedQuotes)
                    } else {
                        Text(stringResource(R.string.loading))
                    }
                }
            }
        }
    }
}

fun loadQuotesFromAssets(context: Context, fileName: String = "inspirational_quotes.json"): List<Quote> {
    return try {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        Json.decodeFromString<List<Quote>>(jsonString)
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        emptyList() // Return empty list on error
    } catch (serializationException: Exception) {
        serializationException.printStackTrace()
        emptyList()
    }
}