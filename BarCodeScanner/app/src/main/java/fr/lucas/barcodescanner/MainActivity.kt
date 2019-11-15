package fr.lucas.barcodescanner

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.room.*

import androidx.lifecycle.ViewModelProviders
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.nio.charset.CodingErrorAction.REPLACE
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "EmployeeDB").build()

        Thread {
            var emp = Emp_Entity()
            emp.emp_id = 2
            emp.emp_name = "Lulu"
            emp.emp_post = "admin6"

            db.empDAO().saveEmp(emp)
            db.empDAO().readEmp().forEach{
                Log.i("@AKTDEV", "Id is : ${it.emp_id}" )
                Log.i("@AKTDEV", "Name is : ${it.emp_name}" )
                Log.i("@AKTDEV", "Post is : ${it.emp_post}" )
            }
        }.start()




        takePhoto.setOnClickListener {
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
            //var intent = Intent(this, CameraActivity::class.java)
            //startActivity(intent)
            //dialog.show()

        }

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]
        viewModel.getState().observe(this, Observer { updateUi(it!!) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == 1) {
               val barcode = data?.getStringExtra("barcode")!!
                Log.v("coucou", "bug")
                viewModel.findProduct(barcode)
            }


        }

    private fun updateUi(state: MainViewModelState) {
        return when(state){
            is MainViewModelState.Success -> {
                var a = state.foodFacts
                Toast.makeText(this@MainActivity, a?.product?.product_name.toString(), LENGTH_SHORT).show()
            }
        }
    }*/


}


