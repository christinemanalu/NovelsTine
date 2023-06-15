package org.d3if3003.novelstine.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3003.novelstine.model.Novel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/" + "TaufiqAshariRamadhan/Assessment1MobPro/static-api/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface NovelApiService {
    @GET("static-api.json")
    suspend fun getNovel(): List<Novel>
}

object NovelApi {

    enum class ApiStatus { LOADING, SUCCESS, FAILED }

    val service: NovelApiService by lazy {
        retrofit.create(NovelApiService::class.java)
    }

    fun getNovelUrl(imageId: String): String {
        return "$BASE_URL$imageId.png"
    }
}