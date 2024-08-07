package com.example.crimeadigital.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarCompose(
    title: String,
    onSwitchLayoutClick: () -> Unit,
    switchIcon: ImageVector,
    contentDescription: String,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    showSearchBar: Boolean = false,
    searchText: String = "",
    onSearchTextChange: (String) -> Unit = {},
    onSearchClick: () -> Unit = {},
    onToggleSearchBar: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.shadow(4.dp, RectangleShape),
        title = {
            if (showSearchBar) {
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Search...") },
                    colors = TextFieldDefaults.colors(
                        MaterialTheme.colorScheme.surface,
                        cursorColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            } else {
                Text(text = title, color = MaterialTheme.colorScheme.primary)
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = onToggleSearchBar) {
                Icon(
                    imageVector = if (showSearchBar) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = if (showSearchBar) "Close" else "Search"
                )
            }
            if (showSearchBar) {
                IconButton(onClick = onSearchClick) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
            IconButton(onClick = onSwitchLayoutClick) {
                Icon(
                    imageVector = switchIcon,
                    contentDescription = contentDescription
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppBarComposePreview() {
    AppBarCompose(title = "Custom App Bar", onSwitchLayoutClick = {}, switchIcon = Icons.AutoMirrored.Filled.List, contentDescription = "Crimea")
}
