package com.kashapovrush.githubrestapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navigation.AppNavGraph
import com.example.navigation.rememberNavigationState
import com.kashapovrush.githubrestapi.ui.theme.GitHubRESTAPITheme
import com.kashapovrush.repository_user.ui.UserInfoScreen
import com.kashapovrush.repository_user.viewmodel.UserInfoViewModel
import com.kashapovrush.search_repository_sreen.ui.SearchRepositoryScreen
import com.kashapovrush.search_repository_sreen.viewmodel.SearchRepositoriesViewModel
import com.kashapovrush.util.ViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as GitHubApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        setContent {
            val searchRepositoriesViewModel: SearchRepositoriesViewModel =
                viewModel(factory = viewModelFactory)
            val userInfoViewModel: UserInfoViewModel = viewModel(factory = viewModelFactory)
            GitHubRESTAPITheme {

                val navigationState = rememberNavigationState()

                AppNavGraph(
                    navHostController = navigationState.navHostController,
                    searchRepositoriesContent = {
                        SearchRepositoryScreen(
                            viewModel = searchRepositoriesViewModel,
                            navigationState = navigationState
                        )
                    },
                    userInfoContent = { login ->
                        UserInfoScreen(userInfoViewModel, login)
                    })
//                TestTwo()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Test() {

    val list = listOf(
        Item("Home", Icons.Outlined.Home, Icons.Filled.Home),
        Item("Shop", Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart),
        Item("Call", Icons.Outlined.Call, Icons.Filled.Call),
        Item("Home", Icons.Outlined.Home, Icons.Filled.Home),
        Item("Shop", Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart),
        Item("Call", Icons.Outlined.Call, Icons.Filled.Call)
    )

    val pagerState = rememberPagerState {
        list.size
    }

    var stateIndex by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(stateIndex) {
        pagerState.animateScrollToPage(stateIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        stateIndex = pagerState.currentPage
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(selectedTabIndex = stateIndex) {
            list.forEachIndexed { index, item ->
                Tab(
                    selected = index == stateIndex,
                    onClick = { stateIndex = index },
                    text = { Text(text = item.title) },
                    icon = { if (index == stateIndex) item.selectedIcon else item.unselectedIcon })
            }


        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = list[it].title, style = MaterialTheme.typography.titleLarge)
            }
        }


    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TestTwo() {

    val list = listOf(
        Item("Home", Icons.Outlined.Home, Icons.Filled.Home),
        Item("Shop", Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart),
        Item("Call", Icons.Outlined.Call, Icons.Filled.Call),
        Item("Home", Icons.Outlined.Home, Icons.Filled.Home),
        Item("Shop", Icons.Outlined.ShoppingCart, Icons.Filled.ShoppingCart),
        Item("Call", Icons.Outlined.Call, Icons.Filled.Call)
    )

    val pagerState = rememberPagerState {
        list.size
    }

    var stateIndex by remember {
        mutableIntStateOf(0)
    }

    LaunchedEffect(stateIndex) {
        pagerState.animateScrollToPage(stateIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        stateIndex = pagerState.currentPage
    }

    Column(modifier = Modifier.fillMaxSize()) {
        ScrollableTabRow(selectedTabIndex = stateIndex, indicator = {}, containerColor = Color.Transparent, divider = {}) {
            list.forEachIndexed { index, item ->
                Button(
                    onClick = { stateIndex = index },
                    modifier = Modifier.width(160.dp).padding(horizontal = 8.dp),
                    colors =
                    if (stateIndex == index) {
                        ButtonDefaults.buttonColors(containerColor = Color.Blue)
                    } else {
                        ButtonDefaults.buttonColors(
                            containerColor = Color.Gray
                        )
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = item.title)
                }
//                Tab(
//                    selected = index == stateIndex,
//                    onClick = { stateIndex = index },
//                    text = { Text(text = item.title) },
//                    icon = { if (index == stateIndex) item.selectedIcon else item.unselectedIcon })
            }


        }

        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = list[it].title, style = MaterialTheme.typography.titleLarge)
            }
        }


    }

}

data class Item(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)
