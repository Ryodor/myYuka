package fr.lucas.barcodescanner

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(val product_name: String, val image_front_url: String)

@JsonClass(generateAdapter = true)
data class FoodFacts(var code: String, val product: Product)