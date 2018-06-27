package com.example.ameen.xiot_internship_assignment.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.ameen.xiot_internship_assignment.UI.MessageFragment;
import com.example.ameen.xiot_internship_assignment.UI.UserPanelActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageService extends Service {

    private MqttAndroidClient mClient; //Current client

    private static final String TAG = "MessageService";

    @Override
    public void onCreate() {
        super.onCreate();


        mClient = UserPanelActivity.mClient; //Getting current client

        mClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable throwable) {
                Toast.makeText(MessageService.this, "Connection lost ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {

                Log.i(TAG, "messageArrived: ");
                MessageFragment.newMessage(s, new String(mqttMessage.getPayload()));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.d(TAG, "onStartCommand");
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
