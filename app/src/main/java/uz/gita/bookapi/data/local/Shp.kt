package uz.gita.bookapi.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Shp @Inject constructor(@ApplicationContext private val context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("Local_storage", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    var token: String
        get() = sharedPreferences.getString("TOKEN", "")!!
        set(value) = editor.putString("TOKEN", value).apply()

}