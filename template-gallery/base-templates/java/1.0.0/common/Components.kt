package {{applicationId}}.common

import android.media.Rating
import android.widget.Toolbar
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import {{applicationId}}.ui.theme.Shapes
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Tag(text: String) {
    Card(
        shape = CircleShape.copy(CornerSize(30))
    ) {
        Text(
            text = text, fontWeight = FontWeight.Normal, fontSize = 12.sp, modifier = Modifier
                .background(Color.LightGray)
                .padding(vertical = 2.dp, horizontal = 18.dp)
        )
    }
}

@Composable
fun Loader() = CircularProgressIndicator()

@Composable
fun CustomComp(
    indicatorValue: Int = 0,
    maxValue: Int = 100,
    backgroundColor: Color = Color.LightGray,
    forgroundColor: Color = Color.Blue,
    stockWidth: Float = 100f,
    canvasSize: Dp = 300.dp
) {
    val allowedIndVal = if (indicatorValue <= maxValue) indicatorValue else maxValue

    var animatedIndVal by remember { mutableStateOf(0f) }
    LaunchedEffect(key1 = allowedIndVal) {
        animatedIndVal = allowedIndVal.toFloat()
    }
    val percent = (animatedIndVal / maxValue) * 100

    val sweepAngel by animateFloatAsState(
        targetValue = (2.4 * percent).toFloat(), animationSpec = tween(
            1000
        )
    )

    val counterAnime by animateIntAsState(targetValue = allowedIndVal, animationSpec = tween(1000))

    Column(
        modifier = Modifier
            .size(canvasSize)
            .drawBehind {
                val iss = size / 1.30f
                drawBackground(
                    componentSize = iss,
                    indicatorColor = backgroundColor,
                    stockWidth = stockWidth
                )
                drawForeground(
                    componentSize = iss,
                    indicatorColor = forgroundColor,
                    stockWidth = stockWidth,
                    sweepAngel = sweepAngel
                )
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmbeddedElements(bigText = counterAnime, bigTextSuffix = "%", smallText = "Mark")
    }
}

fun DrawScope.drawBackground(
    componentSize: Size,
    indicatorColor: Color,
    stockWidth: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = stockWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2,
            y = (size.height - componentSize.height) / 2,
        )
    )
}

fun DrawScope.drawForeground(
    componentSize: Size,
    indicatorColor: Color,
    stockWidth: Float,
    sweepAngel: Float
) {
    drawArc(
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngel,
        useCenter = false,
        style = Stroke(
            width = stockWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2,
            y = (size.height - componentSize.height) / 2,
        )
    )
}

@Composable
fun EmbeddedElements(
    bigText: Int,
    bigTextSuffix: String,
    smallText: String
) {

    Text(text = smallText, fontSize = 18.sp, color = Color.LightGray)
    Text(text = "$bigText ${bigTextSuffix.take(2)}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun CustomTimer(
    percentage: Int = 0
) {
    val dots = 59
    val offsetAngleDegree = 4
    val lineDegree = (360 - (offsetAngleDegree * 2))
    val radius = 400
    val dotRadius = 6f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .drawBehind {
                for (dot in 0..dots) {
                    val angleInDeg = lineDegree * dot - 90f + offsetAngleDegree
                    val angleRad = Math
                        .toRadians(angleInDeg.toDouble())
                        .toFloat()
                    val x = radius * cos(angleRad) + size.center.x
                    val y = radius * sin(angleRad) + size.center.y
                    println("Coords $dot x->$x, y->$y, angleInDeg $angleInDeg $angleRad")
                    drawCircle(
                        color = if (dot == 0) Color.LightGray else Color.Black, center = Offset(
                            x = x,
                            y = y
                        ), radius = dotRadius
                    )
                }
            }, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$percentage")
    }
}

@ExperimentalMaterialApi
@Composable
fun SignInButton(
    text: String,
    loadingText: String = "Signing in...",
    icon: Painter,
    isLoading: Boolean = false,
    shape: Shape = Shapes.medium,
    borderColor: Color = Color.LightGray,
    backgroundColor: Color = MaterialTheme.colors.surface,
    progressIndicatorColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clickable(
                enabled = !isLoading,
                onClick = onClick
            )
            .fillMaxWidth(),
        shape = shape,
        border = BorderStroke(width = 1.dp, color = borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 10.dp,
                    bottom = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = icon,
                contentDescription = "SignInButton",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (isLoading) loadingText else text)
            if (isLoading) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(16.dp)
                        .width(16.dp),
                    strokeWidth = 2.dp,
                    color = progressIndicatorColor
                )
            }
        }
    }
}

class CustomShape : Shape {
    override fun createOutline(size: Size,layoutDirection: LayoutDirection, density: Density): Outline {
        val path = Path().apply {
            moveTo(size.width / 2F, size.height / 2F)
            lineTo(size.width, 0f)
            lineTo(0f, 0f)

//            lineTo(size.width / 2f, size.height / 2)
//            lineTo(0f, 0f)


//            moveTo(size.width / 2f, 0f)
//            lineTo(size.width, size.height)
//            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun Toolbar() {
    Column(
        Modifier.padding(18.dp)
            .width(900.dp)
            .height(200.dp)
        .drawBehind {

        }.background(shape = CustomShape(), color = Color.Black),
    ) {
        Text(text = "Testing....", color = Color.White)
    }
}


@Composable
@Preview(showBackground = true)
fun TestPreview() {
    Toolbar()
}


