package com.kashapovrush.repository_user.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kashapovrush.repository_user.viewmodel.UserInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun UserInfoScreen(viewModel: UserInfoViewModel, login: String) {
    Log.d("MainActivityTest", "RECOMPOSITION")

    val scope = rememberCoroutineScope()

    scope.launch {
        viewModel.getUser(login = login)
    }

    val state = viewModel.user.collectAsState()
    Content(state)
}

@Composable
private fun Content(state: State<UserState>) {
    when (val currentStateUser = state.value) {
//        is UserState.Load -> Loading()
        is UserState.Success -> ContentUser(currentStateUser)
        is UserState.Error -> Error(currentStateUser.message)
        is UserState.Initial -> {}
    }
}

@Composable
fun Error(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = message)
    }
}


@Composable
private fun ContentUser(
    currentStateUser: UserState.Success
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = currentStateUser.user.avatar_url,
            contentDescription = null,
            modifier = Modifier
                .shadow(
                    shape = CircleShape,
                    elevation = 4.dp
                )
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconWithText(
                image = Icons.Default.Work,
                text = currentStateUser.user.public_repos.toString()
            )
            Spacer(modifier = Modifier.width(16.dp))
            IconWithText(
                image = Icons.Default.People,
                text = currentStateUser.user.followers.toString()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = currentStateUser.user.bio ?: "",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        )
    }
}

@Composable
fun IconWithText(image: ImageVector, text: String?) {
    Image(
        imageVector = image,
        contentDescription = null,
        modifier = Modifier.size(24.dp)
    )
    Spacer(modifier = Modifier.width(8.dp))

    Text(text = text ?: "", fontSize = 24.sp)
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .height(64.dp)
                .width(64.dp)
        )
    }
}
