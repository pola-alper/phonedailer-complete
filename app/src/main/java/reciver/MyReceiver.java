package reciver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.alper.pola.andoid.phonedailer.MainActivity;

/**
 * Created by pola alper on 24-Dec-16.
 */

public class MyReceiver extends BroadcastReceiver {
    static int countPowerOff=0;
    private Activity activity=null;
    public MyReceiver (Activity activity)
    {
        this.activity=activity;
    }

    public MyReceiver()
    {

    }
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.v("onReceive", "Power button is pressed.");



        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            countPowerOff++;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            if (countPowerOff >= 1) {

                Intent i = new Intent(activity, MainActivity.class);
                activity.startActivity(i);
            }
        }

    }}