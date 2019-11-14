package fr.lucas.barcodescanner

import android.Manifest
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.os.Bundle
import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.Result
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



private const val TAG = "CameraActivity"
private const val scanResult = "3228886030011"

class CameraActivity : Activity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null

    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)

        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)                // Set the scanner view as the content view

    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) {
       /* val url = "https://world.openfoodfacts.org/api/v0/product/737628064502.json/"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retrofit.create<FoodFactsService>(FoodFactsService::class.java)
        val foodFactsRequest = service.listFoodFacts()

        foodFactsRequest.enqueue(object: Callback<List<FoodFacts>>{
            override fun onResponse(
                call: Call<List<FoodFacts>>,
                response: Response<List<FoodFacts>>
            ) {
                Log.v("reqResponse", response.body().toString())
            }

            override fun onFailure(call: Call<List<FoodFacts>>, t: Throwable) {
                error("Failed Request")
            }
        })*/

        // Do something with the result here
        Log.v(TAG, rawResult.getText()) // Prints scan results
        Log.v(
            TAG,
            rawResult.getBarcodeFormat().toString()
        ) // Prints the scan format (qrcode, pdf417 etc.)

        // If you would like to resume scanning, call this method below:
        mScannerView!!.resumeCameraPreview(this)
    }
}