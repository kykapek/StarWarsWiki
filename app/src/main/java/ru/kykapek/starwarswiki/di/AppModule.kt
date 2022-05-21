package ru.kykapek.starwarswiki.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kykapek.starwarswiki.api.ApiService
import ru.kykapek.starwarswiki.data.FilmsRepository
import ru.kykapek.starwarswiki.data.database.FilmsDB
import ru.kykapek.starwarswiki.data.database.FilmsDBRepository
import ru.kykapek.starwarswiki.data.database.FilmsDao
import ru.kykapek.starwarswiki.data.remote.FilmsRemoteDataSource
import ru.kykapek.starwarswiki.data.remote.FilmsService
import ru.kykapek.starwarswiki.utils.Constants.ROOT_API
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesBaseUrl(): String {
        return ROOT_API
    }

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        baseUrl: String,
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)

        return retrofit.build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun providesFilmsRepository(apiService: ApiService): FilmsRepository {
        return FilmsRepository(apiService)
    }



    @Singleton
    @Provides
    fun provideFilmsService(retrofit: Retrofit) : FilmsService = retrofit.create(FilmsService::class.java)

    @Singleton
    @Provides
    fun provideFilmsRemoteDataSource(filmsService: FilmsService) = FilmsRemoteDataSource(filmsService)

    @Singleton
    @Provides
    fun provideFilmsDao(db: FilmsDB) = db.filmsDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = FilmsDB.getDatabase(context)

    @Singleton
    @Provides
    fun provideFilmsDbRepository(remoteDataSource: FilmsRemoteDataSource,
                                    filmsDao: FilmsDao) =
        FilmsDBRepository(remoteDataSource, filmsDao)

}