package fr.lucas.barcodescanner

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

sealed class MainViewModelState {
    data class Success(val foodFacts: FoodFacts?): MainViewModelState()
}

class MainViewModel: ViewModel() {
    private val state = MutableLiveData<MainViewModelState>()
    fun getState(): LiveData<MainViewModelState> = state

    fun findProduct(barcode: String) {
        ///Req Api
        val url = " https://world.openfoodfacts.org/api/v0/"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create<FoodFactsService>(FoodFactsService::class.java)
        //val foodFactsRequest = service.listFoodFacts("3228886030011")
        val foodFactsRequest = service.listFoodFacts(barcode)

        foodFactsRequest.enqueue(object: Callback<FoodFacts> {
            override fun onResponse(
                call: Call<FoodFacts>,
                response: Response<FoodFacts>
            ) {
                if(response.isSuccessful()){
                    state.value = MainViewModelState.Success(response.body())
                    Log.v("reqResponse", response.body()?.product?.product_name)
                }
            }

            override fun onFailure(call: Call<FoodFacts>, t: Throwable) {
                Log.v("reqResponse", "Failed Request: $t")
            }
        })
    }

}