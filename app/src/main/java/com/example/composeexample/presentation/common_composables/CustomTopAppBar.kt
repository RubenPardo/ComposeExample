package com.example.composeexample.presentation.common_composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@Composable
fun CustomTopAppBar(title:String, backCallback: (()->Unit)?,contentDescription: String = ""){

    TopAppBar (
        navigationIcon  = if (backCallback != null) {
            {
                IconButton(
                    modifier = Modifier.semantics {
                      this.contentDescription = contentDescription
                    },
                    onClick = { backCallback.invoke() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        },
        title = { Text(title,color= Color.White) },
        backgroundColor = MaterialTheme.colorScheme.primary
    )

}