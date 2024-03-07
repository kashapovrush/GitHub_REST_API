package com.kashapovrush.repository_user

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UserInfoScreen(viewModel: UserInfoViewModel, login: String) {

    CoroutineScope(Dispatchers.IO).launch {
        viewModel.getUser(login = login)
    }

    val state = viewModel.user.observeAsState()
    val currentStateUser = state.value

    when(currentStateUser) {
        UserState.Load -> {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.height(64.dp).width(64.dp))
            }
        }
        is UserState.Success -> {
            Scaffold(modifier = Modifier.fillMaxSize(),
                content = {
                    Column(
                        modifier = Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Box(
                            modifier = Modifier
                                .width(150.dp)
                                .height(150.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(model = currentStateUser.user.avatar_url, contentDescription = null)
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = currentStateUser.user.bio ?: "", fontSize = 24.sp)
                        }
                        Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                        contentAlignment = Alignment.Center
                        ) {
                        Text(text = "public repos ${currentStateUser.user.public_repos}", fontSize = 24.sp)
                    }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "followers ${currentStateUser.user.followers}", fontSize = 24.sp)
                        }
                    }
                })
        }

        null -> {}
    }



}
