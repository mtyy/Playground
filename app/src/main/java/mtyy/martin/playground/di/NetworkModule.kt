package mtyy.martin.playground.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import mtyy.martin.playground.data.network.NewsService
import okhttp3.MediaType
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class) //used to be ApplicationComponent on in Dagger 2.30 and below.
class NetworkModule {

    @Provides
    @JsonConverterFactory
    fun provideJsonConverter() : Converter.Factory {
        val contentType = MediaType.parse("application/json")!!
        val json = Json { ignoreUnknownKeys = true }
        return json.asConverterFactory(contentType)
    }

    @Provides
    fun provideRetrofit(@JsonConverterFactory jsonConverterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spaceflightnewsapi.net/v4/")
            .addConverterFactory(jsonConverterFactory)
            .build()
    }

    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }
}

/**
 * Im afraid we can easily have more converters later, thus created this eagerly.
 */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class JsonConverterFactory