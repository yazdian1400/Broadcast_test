package ir.homework.broadcast

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var intentFilter = IntentFilter()
    val smsBroadcastReciever = SmsBroadcastReciever()
    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "you granted this permission", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "you denied this permission", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED")

        registerReceiver(smsBroadcastReciever, intentFilter)

        getPermission()
    }

    fun getPermission() {
        requestPermissionLauncher.launch(
            Manifest.permission.RECEIVE_SMS
        )
    }
}