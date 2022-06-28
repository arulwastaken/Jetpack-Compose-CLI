package {{applicationId}}.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
        body1 = TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
        ),
        h2 = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
        ),
        h3 = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
        ),
        h4 = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
        )/* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)