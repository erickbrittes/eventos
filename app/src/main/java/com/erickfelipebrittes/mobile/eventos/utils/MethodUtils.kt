package com.erickfelipebrittes.mobile.eventos.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDateTime(dateTime: String): String {
    val calendar = Calendar.getInstance()
    calendar.time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(dateTime)

    val returnFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR"))
    return returnFormat.format(calendar.time)
}

fun formatReal(value: Long): String {
    val ptBr = Locale("pt", "BR")
    val valorString: String = NumberFormat.getCurrencyInstance(ptBr).format(value)
    return valorString
}

fun convertStringToCalendar(timeMillis: Long): String? {
    //get calendar instance
    val sdf = java.text.SimpleDateFormat("dd/MM/yyyy - HH:mm:ss'Z'")
    val date = java.util.Date(timeMillis * 1000)
    return sdf.format(date)
}

fun getCurrentDateTime(): String {
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale("pt", "BR"))
    return format.format(calendar.time)
}

fun getStringSizeLengthFile(size: Long): String? {
    val df = DecimalFormat("0.00")
    val sizeKb = 1024.0f
    val sizeMb = sizeKb * sizeKb
    val sizeGb = sizeMb * sizeKb
    val sizeTerra = sizeGb * sizeKb
    if (size < sizeMb) return df.format(size / sizeKb)
        .toString() + " Kb" else if (size < sizeGb) return df.format(size / sizeMb)
        .toString() + " Mb" else if (size < sizeTerra) return df.format(size / sizeGb)
        .toString() + " Gb"
    return ""
}
