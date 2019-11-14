package fr.lucas.barcodescanner

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        takePhoto.setOnClickListener{
            //viewModel = MainViewModel()
            //viewModel.openCamera(this)

            ///Req Api
            /*val url = " https://world.openfoodfacts.org/api/v0/"

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            val service = retrofit.create<FoodFactsService>(FoodFactsService::class.java)
            val foodFactsRequest = service.listFoodFacts("3228886030011")

            foodFactsRequest.enqueue(object: Callback<FoodFacts> {
                override fun onResponse(
                    call: Call<FoodFacts>,
                    response: Response<FoodFacts>
                ) {
                    if(response.isSuccessful()){
                        Log.v("reqResponse", response.body()?.product?.product_name)
                    }
                }

                override fun onFailure(call: Call<FoodFacts>, t: Throwable) {
                    Log.v("reqResponse", "Failed Request: $t")
                }
            })*/

            ///Verif Droit CAMERA
            /*if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this , android.Manifest.permission.CAMERA)){

            } else {
              ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 1)
            }
            } else {
                Toast.makeText(this@MainActivity, "Droit activé", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, CameraActivity::class.java)
                startActivity(intent)
            }*/

        }

        //viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        //viewModel.getState().observe(this, Observer { updateUi(it!!) })
    }

    /*private fun updateUi(state: MainViewModelState) {
        return when(state){
            is MainViewModelState.Success -> {
                Toast.makeText(this@MainActivity, "oaoaooa", Toast.LENGTH_SHORT).show()
            }
        }
    }*/
}
