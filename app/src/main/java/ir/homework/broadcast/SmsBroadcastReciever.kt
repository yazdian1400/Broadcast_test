package ir.homework.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log

class SmsBroadcastReciever: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d("-----sms", p1?.action.toString())

        val bundle = p1?.extras
        if (bundle != null && bundle.containsKey("pdus")) {
            val pdus = bundle["pdus"] as Array<*>?
            val sms: SmsMessage = SmsMessage.createFromPdu(pdus!![0] as ByteArray)
            val senderNumber: String? = sms.getOriginatingAddress()

            Log.d("-----sms", senderNumber.toString())
            Log.d("-----sms", sms.toString())
        }

    }


}