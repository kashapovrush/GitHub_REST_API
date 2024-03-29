package com.kashapovrush.search_repository_sreen.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.navigation.NavigationState
import com.example.navigation.Screen
import com.kashapovrush.api.modelDto.SearchItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SearchRepositoryScreen(
    viewModel: SearchRepositoriesViewModel,
    navigationState: NavigationState
) {
    val scope = CoroutineScope(Dispatchers.IO)

    val text = remember {
        mutableStateOf("")
    }

    val active = remember {
        mutableStateOf(false)
    }

    val state = viewModel.screenState.observeAsState()
    val currentScreenState = state.value
    Column {
        SetSearchBar(text, active) {
            scope.launch {
                viewModel.searchRepositories(text.value)

            }
        }

        Scaffold(
            content = { paddingValues ->
                when (currentScreenState) {
                    is ScreenState.Loading -> {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize().padding(paddingValues = paddingValues)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .width(48.dp)
                                    .height(48.dp)
                            )
                        }

                    }

                    is ScreenState.Error -> Log.d(
                        "MainActivityTest",
                        "Error ${currentScreenState.message}"
                    )

                    is ScreenState.ShowResult -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(items = currentScreenState.repositories, key = { it.id }) {
                                SetRepository(item = it) {
                                    navigationState.navigateToUserInfo(it.owner?.login ?: "")
                                }
                            }
                        }
                    }

                    else -> {}
                }

            }, bottomBar = {
                SetBottomBar()
            })
    }
}

@Composable
private fun SetBottomBar() {
    BottomAppBar {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = null)
            }
        }
    }
}


@Composable
fun SetRepository(item: SearchItem, navigateToUserInfo: () -> Unit) {
    Row(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .padding(8.dp).clickable {
                navigateToUserInfo()
            }
    ) {
        AsyncImage(
            model = item.owner?.avatar_url,
            contentDescription = null,
            modifier = Modifier
                .height(64.dp)
                .width(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = item.full_name ?: "", fontSize = 16.sp)
            Text(
                text = item.description ?: "",
                fontSize = 10.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }


    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SetSearchBar(
    text: MutableState<String>,
    active: MutableState<Boolean>,
    listener: () -> Unit
) {

    SearchBar(
        query = text.value,
        onQueryChange = {
            text.value = it
        },
        onSearch = {
            active.value = false
            listener()
        },
        active = active.value,
        onActiveChange = {
            active.value = it
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = "search repository")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
        trailingIcon = {
            if (active.value) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.value.isNotEmpty()) {
                            text.value = ""
                        } else {
                            active.value = false
                        }
                    }, imageVector = Icons.Default.Close,
                    contentDescription = null
                )
            }
        },
        colors = SearchBarDefaults.colors()
    ) {

    }
}