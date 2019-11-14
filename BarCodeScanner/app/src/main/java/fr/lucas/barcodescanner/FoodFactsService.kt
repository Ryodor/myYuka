package fr.lucas.barcodescanner

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodFactsService {
    @GET("product/{barcode}.json")
    fun listFoodFacts(@Path("barcode") barcode: String): Call<FoodFacts>
}