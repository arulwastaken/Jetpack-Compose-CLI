package {{applicationId}}.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import {{applicationId}}.R


const val ROOT_ROUTE = "root_route"
const val HOME_ROUTE = "main_route"
// routeConst

sealed class ScreenRoute(val route: String) {

    object LoginScreen : ScreenRoute("login_screen")
    object SignupScreen : ScreenRoute("signup_screen")

    object HomeSCreen : ScreenRoute("home_screen")
    object BookMarkScreen : ScreenRoute("home_bookmark_screen")
    object ProfileScreen : ScreenRoute("home_profile_screen")
    // screenRoute
}

sealed class BottomNavScreen(
    val route: String,
    val label: String,
    val icon: ImageVector? = null,
    val iconImg: Int? = null
) {
    //Bottom Nav
    object Home : BottomNavScreen(ScreenRoute.NewsListScreen.route, "Home", Icons.Outlined.Home)

    object BookMark :
        BottomNavScreen(ScreenRoute.BookMarkScreen.route, "Bookmark", iconImg = R.drawable.ic_nav_bookmark_filled)

    object Profile :
        BottomNavScreen(ScreenRoute.ProfileScreen.route, "Profile", Icons.Outlined.AccountCircle)

}
