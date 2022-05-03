package kosmicbor.githubclientapp.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module
class HttpClientModule {

    @Provides
    fun getHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {

        Timber.plant(Timber.DebugTree())

        val interceptor = HttpLoggingInterceptor {
            Timber.i(it)
        }

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return interceptor
    }
}