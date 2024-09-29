package com.books.testtask.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.books.testtask.domen.entities.User
import com.books.testtask.presentation.utils.Constants
import com.books.testtask.presentation.viewmodels.MainActivityViewModel

@Composable
fun UserListScreen(
        viewModel: MainActivityViewModel,
        modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

    val users by viewModel.users.observeAsState()

    val filteredUsers = if (searchQuery.isEmpty()) {
        viewModel.users.value
    } else {
        viewModel.users.value?.filter { it.login.contains(searchQuery, ignoreCase = true) }
    }

    Column(
            modifier = modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .fillMaxSize()
                    .background(Color.Black)
    ) {
        if (users != null) {
            UserSearchBar(
                    viewModel,
                    query = searchQuery,
                    onQueryChange = { newQuery -> searchQuery = newQuery }
            )
        } else {
            Box (
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
                            .background(Color.White)
                            .clip(shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                        text = Constants.NO_USERS_FOUND,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                                .padding(8.dp)
                )
            }
        }
        if (isLoading) {
            Box(
                    modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.3f)) // Optional: Semi-transparent background
                            .align(Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator(
                        modifier = Modifier
                                .align(Alignment.Center)
                                .size(100.dp),
                        color = Color.White,
                        strokeWidth = 4.dp
                )
            }
        }

        if (filteredUsers?.isEmpty() == true) {
            Text(
                    Constants.NO_USERS_FOUND,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(filteredUsers ?: emptyList()) { user ->
                    PersonView(user = user)
                }
            }
        }
    }
}