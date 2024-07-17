package com.example.crimeadigital.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarCompose(
    title: String,
    onSwitchLayoutClick: () -> Unit,
    switchIcon: ImageVector,
    contentDescription: String,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(text = title)
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
fun AppBarComposePreview(){
    AppBarCompose(title = "Custom App Bar", onSwitchLayoutClick = {}, switchIcon = Icons.AutoMirrored.Filled.List, contentDescription = "Crimea")
}