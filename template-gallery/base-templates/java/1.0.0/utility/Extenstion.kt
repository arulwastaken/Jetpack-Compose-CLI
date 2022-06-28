package {{applicationId}}.utility

import android.text.format.DateUtils
import com.google.common.hash.Hashing
import com.google.gson.Gson
import java.nio.charset.StandardCharsets
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun <T> T.toJsonString(): String = Gson().toJson(this)

fun String.dateToTimeAgo(): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    sdf.setTimeZone(TimeZone.getTimeZone("GMT"))
    var ago: CharSequence? = null
    try {
        val time: Long = sdf.parse("2016-01-24T16:00:00.000Z").getTime()
        val now = System.currentTimeMillis()
        ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS) as String?
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ago.toString() ?: null
}

val appLocale = Locale("EN", "IN")
val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss'Z'", appLocale)
val normalFormat = SimpleDateFormat("dd-MM-yyyy", appLocale)

fun Date.currentDateAsIso(): String = isoFormat.format(this)
fun String.convertIsoToDate(): Date = isoFormat.parse(this)
fun String.isoToNormalDate(): String = normalFormat.format(this.convertIsoToDate())
fun Date.isYesterday(): Boolean = DateUtils.isToday(this.time + DateUtils.DAY_IN_MILLIS)
fun Date.isToday(): Boolean = DateUtils.isToday(this.time)

fun String.toDateSection(): String {
    val convertedDate = this.convertIsoToDate()
    return when {
        convertedDate.isToday() -> {
            "Today"
        }
        convertedDate.isYesterday() -> {
           "Yesterday"
        }
        else -> {
            normalFormat.format(convertedDate)
        }
    }
}


fun String?.toSha256(): String {
    return Hashing.sha256()
        .hashString(this, StandardCharsets.UTF_8)
        .toString()
}