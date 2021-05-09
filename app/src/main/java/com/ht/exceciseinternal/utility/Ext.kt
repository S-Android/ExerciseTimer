package com.ht.exceciseinternal.utility

import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.constraintlayout.widget.Group
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.ht.exceciseinternal.ExerciseInternalApp
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.Duration
import com.ht.exceciseinternal.widgets.BaseWC
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.regex.Pattern

fun String?.isNotNullAndBlank(): String? = if (isNullOrBlank()) null else this

fun Group.setAllOnClickListener(listener: (view: View) -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

fun Group.setAllEnabled(isEnabled: Boolean) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).isEnabled = isEnabled
    }
}

fun <R, A, B> ifNotNull(p1: A?, p2: B?, function: (p1: A, p2: B) -> R): R? = p1?.let { p2?.let { function.invoke(p1, p2) } }


private val decimalFormatter: DecimalFormat = DecimalFormat("#,###.00").apply { roundingMode = RoundingMode.HALF_UP }
fun Double.format() = decimalFormatter.format(this).let {
    val formattedString = if (it[0] == '.') {
        "0$it"
    } else {
        it
    }.replace(".00", "")

    formattedString
}

fun String.isValidEmail() =
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPasswordFormat(): Boolean {
    val passwordREGEX = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=\\S+$)" +           //no white spaces
            ".{8,}" +               //at least 8 characters
            "$");
    return passwordREGEX.matcher(this).matches()
}

fun String.isValidPan(): Boolean {
    val passwordREGEX = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}")
    return passwordREGEX.matcher(this).matches()
}

fun Int?.dp2px(): Float = UIUtils.dpToPx(ExerciseInternalApp.instance, this?.toFloat() ?: 0f).toFloat()
fun Float?.dp2px(): Float = UIUtils.dpToPx(ExerciseInternalApp.instance, this ?: 0f).toFloat()
fun Double?.dp2px(): Float = UIUtils.dpToPx(ExerciseInternalApp.instance, this?.toFloat() ?: 0f).toFloat()
fun Int?.dp2pxInt(): Int = UIUtils.dpToPx(ExerciseInternalApp.instance, this?.toFloat() ?: 0f)
fun Float?.dp2pxInt(): Int = UIUtils.dpToPx(ExerciseInternalApp.instance, this ?: 0f)
fun Double?.dp2pxInt(): Int = UIUtils.dpToPx(ExerciseInternalApp.instance, this?.toFloat() ?: 0f)

inline fun <reified T> JsonObject.fetch(key: String): T? {
    return try {
        var returnValue: T? = null
        if (has(key)) {
            returnValue = Gson().fromJson(get(key)?.asString, object : TypeToken<T?>() {}.type)
        }
        returnValue
    } catch (ex: Exception) {
        null
    }
}

fun MutableList<BaseWC>?.copy(): MutableList<BaseWC> {
    val returnValue = mutableListOf<BaseWC>()
    for (item in this ?: mutableListOf()) {
        returnValue.add(item.copy())
    }
    return returnValue
}

fun Circuit?.isValid(): Boolean {
    this ?: return false

    var returnValue = exerciseList.isNullOrEmpty().not()
    for (exercise in exerciseList ?: mutableListOf()) {
        val validExercise = exercise.name?.trim().isNullOrBlank().not()
                && (exercise.exerciseDuration.min != null || exercise.exerciseDuration.sec != null)
                && (exercise.restDuration.min != null || exercise.restDuration.sec != null)
        if (validExercise.not()) {
            returnValue = false
            break
        }
    }

    return returnValue
}

fun Long?.format(): String? {
    this ?: return null

    var returnValue = this.toString()
    if (returnValue.length == 1) {
        returnValue = "0$returnValue"
    }

    return returnValue
}

fun String?.getRawPath(): String? {
    this ?: return null

    return try {
        "android.resource://com.ht.exceciseinternal/raw/${this}"
    } catch (ex: Exception) {
        null
    }
}