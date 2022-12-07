package uz.gita.bookapi.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Shp @Inject constructor(@ApplicationContext private val context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("Local_storage", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    var token: String
        get() = sharedPreferences.getString("TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyMiwiaXNzIjoiaHR0cDovLzEyNy4wLjAuMTo4MDgyLyIsImV4cCI6MTY3MDE5MTczOX0.Xn8FFtkiVMZwVDsmd_YSeHF4wCt_3UGeG_MgDRayBPY")!!
        set(value) = editor.putString("TOKEN", value).apply()

}