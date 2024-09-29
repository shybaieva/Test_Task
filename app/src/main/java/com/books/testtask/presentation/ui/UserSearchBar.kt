package com.books.testtask.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.books.testtask.presentation.viewmodels.MainActivityViewModel
import java.lang.reflect.Modifier

@Composable
fun UserSearchBar(
        viewModel: MainActivityViewModel,
        query: String,
        onQueryChange: (String) -> Unit,
        modifier: Modifier = Modifier()
) {
    Box (
            modifier = androidx.compose.ui.Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                    .background(Color.Black)
    ) {
        TextField(
                value = query,
                modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color.White),
                onValueChange = onQueryChange,
                placeholder = { Text("Search by nickname") }
        )
    }
}