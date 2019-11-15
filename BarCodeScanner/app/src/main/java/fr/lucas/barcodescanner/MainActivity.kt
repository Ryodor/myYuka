package fr.lucas.barcodescanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var db: AppDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var db = Room.databaseBuilder(applicationContext, AppDB::class.java, "EmployeeDB")
            .allowMainThreadQueries().build()

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
            var intent = Intent(this, CameraActivity::class.java)
            startActivity(intent)
        }

        ///Req Api
        val url = " https://world.openfoodfacts.org/api/v0/"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create<FoodFactsService>(FoodFactsService::class.java)

        val factory = MainViewModelFactory(service)
        viewModel = ViewModelProviders.of(this, factory)[MainViewModel::class.java]
        viewModel.getState().observe(this, Observer { updateUi(it!!) })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                val barcode = data?.getStringExtra("barcode")!!
                Log.v("coucou", "bug")
                viewModel.findProduct(barcode)
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this@MainActivity, "Scan annulé", LENGTH_SHORT).show()
        }


    }

    private fun updateUi(state: MainViewModelState) {
        Log.v("@AZER", "HELLOOOOO")
        return when (state) {

            is MainViewModelState.Success -> {
                var a = state.foodFacts
                Toast.makeText(this@MainActivity, a?.product?.product_name.toString(), LENGTH_SHORT)
                    .show()

                var emp = Emp_Entity(0)
                emp.emp_id = 0
                emp.emp_name = a?.product?.product_name.toString()
                emp.emp_img = a?.product?.image_front_url.toString()


                db.empDAO().saveEmp(emp)
                db.empDAO().readEmp().forEach {
                    Log.i("@YOLO", "id is : ${it.emp_id}")
                    Log.i("@YOLO", "name is : ${it.emp_name}")
                    Log.i("@YOLO", "img is : ${it.emp_img}")
                }

            }
            is MainViewModelState.Failure -> {
                Toast.makeText(this@MainActivity, "Scan error", LENGTH_SHORT).show()
            }
        }
    }


}


