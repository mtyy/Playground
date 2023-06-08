package mtyy.martin.playground.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @DispatcherIo
    fun provideIoDispatcher() : CoroutineDispatcher = Dispatchers.IO

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DispatcherIo