package com.example.moviedbapp.di.modules

import android.content.Context
import com.bumptech.glide.Glide
import com.example.moviedbapp.network.ApiManager
import com.example.moviedbapp.network.RetrofitEndPoints
import com.example.moviedbapp.repo.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getRetrofitInstance() = ApiManager.getInstance()

    @Singleton
    @Provides
    fun getRetroEndPoints(): RetrofitEndPoints =
        getRetrofitInstance().create(RetrofitEndPoints::class.java)

    @Singleton
    @Provides
    fun getRepository() = AppRepository(getRetroEndPoints())

    @Singleton
    @Provides
    fun getGlideInstance(@ApplicationContext context: Context) = Glide.get(context)
}