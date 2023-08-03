package com.example.composeexample.di

import android.content.Context
import androidx.room.Room
import com.example.composeexample.data.datasources.local.implementations.LocalDataSourceRoomImpl
import com.example.composeexample.data.datasources.local.interfaces.LocalDataSource
import com.example.composeexample.data.datasources.local.room.HeroDao
import com.example.composeexample.data.datasources.local.room.HeroDatabase
import com.example.composeexample.data.datasources.remote.apis.DragonBallApi
import com.example.composeexample.data.datasources.remote.implementations.AuthServiceImpl
import com.example.composeexample.data.datasources.remote.implementations.RemoteDataSourceRetrofitImpl
import com.example.composeexample.data.datasources.remote.implementations.SharedPreferencesServiceImpl
import com.example.composeexample.data.datasources.remote.interfaces.AuthService
import com.example.composeexample.data.datasources.remote.interfaces.RemoteDataSource
import com.example.composeexample.data.datasources.remote.interfaces.SharedPreferencesService
import com.example.composeexample.data.repositories.AuthRepositoryImpl
import com.example.composeexample.data.repositories.DragonBallRepositoryImpl
import com.example.composeexample.data.repositories.interfaces.AuthRepository
import com.example.composeexample.data.repositories.interfaces.DragonBallRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val dataModule = module{
    single <AuthRepository>{ AuthRepositoryImpl(get(),get())  }
    single <DragonBallRepository>{ DragonBallRepositoryImpl(get(), get())  }

    single <SharedPreferencesService>{ SharedPreferencesServiceImpl()  }
    single <AuthService>{ AuthServiceImpl()  }
    single <RemoteDataSource>{ RemoteDataSourceRetrofitImpl(get())  }
    single <LocalDataSource>{ LocalDataSourceRoomImpl(get())  }

    single<DragonBallApi>{
        getDragonBallService(get())
    }

    single {
        getDatabase(get())
    }

    single {
        providesHeroDao(get())
    }


    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT).apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    single<Retrofit>{
        Retrofit.Builder()
            .baseUrl("https://dragonball.keepcoding.education/api/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single<Moshi>{
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }


}

private fun getDragonBallService(retrofit: Retrofit) = retrofit.create(DragonBallApi::class.java)

private fun getDatabase(context: Context) : HeroDatabase =
    Room.databaseBuilder(
        context,
        HeroDatabase::class.java, "superhero-db"
    ).build()

private fun providesHeroDao(db: HeroDatabase) : HeroDao =
    db.superHeroDao()