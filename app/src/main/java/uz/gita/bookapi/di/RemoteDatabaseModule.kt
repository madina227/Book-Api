package uz.gita.bookapi.di

import android.content.Context
import com.mocklets.pluto.Pluto
import com.mocklets.pluto.PlutoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.bookapi.data.remote.service.AuthApi
import uz.gita.bookapi.data.remote.service.BookApi
import uz.gita.bookapi.data.remote.service.UserApi
import uz.gita.bookapi.utils.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDatabaseModule {

    @Provides
    @Singleton
    fun client(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(PlutoInterceptor())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, @ApplicationContext context: Context): Retrofit {
        Pluto.initialize(context)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)


    @Provides
    @Singleton
    fun provideBookApi(retrofit: Retrofit): BookApi = retrofit.create(BookApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

}