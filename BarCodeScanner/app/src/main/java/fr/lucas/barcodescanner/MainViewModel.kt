package fr.lucas.barcodescanner

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

sealed class MainViewModelState {
    object Success: MainViewModelState()
}

class MainViewModel: ViewModel() {
    private val state = MutableLiveData<MainViewModelState>()
    fun getState(): LiveData<MainViewModelState> = state

    fun openCamera() {
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
}