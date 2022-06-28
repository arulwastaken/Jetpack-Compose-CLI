package {{applicationId}}.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import {{applicationId}}.R
import {{applicationId}}.navigation.*
import {{applicationId}}.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route?.substringBeforeLast("/")

            MyApp {
                Scaffold(backgroundColor = MaterialTheme.colors.background, bottomBar = {
                    SetupBottomBar(navController)
                }) {
                    SetupNavGraph(
                        navController = navController,
                        startDestination = HOME_ROUTE
                    )
                }
            }
        }
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    MyApplicationTheme {
        Column {
            content()
        }
    }
}

@Composable
fun SetupBottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        BottomNavScreen.Home,
        BottomNavScreen.BookMark,
        BottomNavScreen.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        screens.forEach {
            AddItem(
                screen = it,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any {
        it.route == screen.route
    } ?: false

    BottomNavigationItem(selectedContentColor = Color.White,
        unselectedContentColor = Color.LightGray,
        icon = {
            if (screen.icon != null) {
                Icon(screen.icon, screen.route)
            } else if(screen.iconImg != null) {
                Icon(painterResource(screen.iconImg), screen.route, modifier = Modifier.height(22.dp))
            }
        },
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route) {
                launchSingleTop = true
            }
        })
}