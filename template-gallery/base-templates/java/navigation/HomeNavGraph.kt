package {{applicationId}}.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import {{applicationId}}.data.remote.dto.Article
import {{applicationId}}.presentation.bookmark.BookmarkScreen
import {{applicationId}}.presentation.news_detail.NewsDetailsScreen
import {{applicationId}}.presentation.home.NewsListScreen
import {{applicationId}}.presentation.profile.ProfileScreen
import {{applicationId}}.presentation.trending.TrendingScreen
import com.google.gson.Gson

@ExperimentalMaterialApi
fun NavGraphBuilder.setupHomeNavGraph(
    navController: NavHostController
) {

    navigation(
        startDestination = ScreenRoute.BookMarkScreen.route,
        route = HOME_ROUTE
    ) {
        composable(
            route = ScreenRoute.NewsListScreen.route,
        ) {
            NewsListScreen(navController)
        }

        composable(
            route = ScreenRoute.TrendingScreen.route,
        ) {
            TrendingScreen(navController)
        }

        composable(
            route = ScreenRoute.BookMarkScreen.route,
        ) {
            BookmarkScreen(navController)
        }
        composable(
            route = ScreenRoute.ProfileScreen.route,
        ) {
            ProfileScreen(navController)
        }

        composable(
            route = ScreenRoute.NewsDetailScreen.route,
            arguments = listOf(
                navArgument("Article") {
                    type = NavType.StringType
                }
            )
        ) {
            val article = Gson().fromJson(it.arguments?.getString("Article"), Article::class.java)
            NewsDetailsScreen(navController, article = article)
        }
    }
}