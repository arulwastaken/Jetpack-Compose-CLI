package {{applicationId}}.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import {{applicationId}}.common.Tag
import {{applicationId}}.data.remote.dto.Article
import {{applicationId}}.data.remote.dto.Source
import {{applicationId}}.ui.theme.Typography
import {{applicationId}}.utility.Constants
import {{applicationId}}.utility.dateToTimeAgo

@ExperimentalMaterialApi
@Composable
fun NewsItem(
    article: Article,
    onItemClick: (Article) -> Unit
) {
    Row(
        Modifier
            .height(150.dp)
            .padding(horizontal = 16.dp)
            .clickable { onItemClick(article) }) {
        Box(Modifier.weight(1F)) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Tag(text = "New")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = article.title, maxLines = 2, style = Typography.h3)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = article.publishedAt.dateToTimeAgo() ?: "-",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Card(
            shape = CircleShape.copy(CornerSize(6)),
            modifier = Modifier
                .width(150.dp)
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .fillMaxHeight(),
            elevation = 10.dp
        ) {
            Image(
                painter = rememberImagePainter(
                    data = if (article.urlToImage == null || article.urlToImage.isEmpty()) Constants.DEFAULT_IMAGE else article.urlToImage,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsItem(
        article = Article(
            "Shady",
            "Shady author b=make book",
            "Shady author b=make bookShady author b=make book",
            "today",
            "",
            Source("", "Art"),
            "Subtle art of Lose Yourself",
            "",
            ""
        ), onItemClick = {

        })
}