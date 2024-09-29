package com.books.testtask.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.books.testtask.domen.entities.User

@Composable
fun PersonView(user: User) {
    Card(
            modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .padding(8.dp)
    ) {
        Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.White)
                        .clip(shape = RoundedCornerShape(8.dp))
        ) {
            AsyncImage(
                    model = user.avatar_url,
                    contentDescription = "Loaded image",
                    modifier = Modifier
                            .size(80.dp)
                            .clip(shape = RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
            )
            Text(
                    text = user.login,
                    fontSize = 24.sp,
                    modifier = Modifier
                            .align(CenterVertically)
                            .padding(horizontal = 16.dp)
            )
        }
    }
}