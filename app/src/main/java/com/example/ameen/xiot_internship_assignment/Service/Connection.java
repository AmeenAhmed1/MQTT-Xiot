package com.example.ameen.xiot_internship_assignment.Service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Connection {

    private static final String TAG = "Connection";

    //Client variable
    private MqttAndroidClient mClient;

    /*
            Function get specific broker and client id
            and try to connect with broker url server
            then return a token with current statues of the connection
         */
    public MqttAndroidClient makeConnection(final Context context, String brokerUrl, String clientId) {

        //initialize the client object
        mClient = new MqttAndroidClient(context, brokerUrl, clientId);

        try {
            Log.i(TAG, "makeConnection: ");
            IMqttToken mToken = mClient.connect(getConnectionOption()); //Token to determine if connection completed or not
            mToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    Log.i(TAG, "onSuccess: 1");
                    mClient.setBufferOpts(getDisconntionOption());
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                    Toast.makeText(context, "Connection failed", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onFailure: 2" + throwable.toString());
                }
            });
        } catch (MqttException e) {
            Log.i(TAG, "makeConnection: Exception");
            e.printStackTrace();
        }
        return mClient;
    }

    private DisconnectedBufferOptions getDisconntionOption() {

        DisconnectedBufferOptions mDisconnectOption = new DisconnectedBufferOptions();
        mDisconnectOption.setBufferEnabled(true);
        mDisconnectOption.setBufferSize(100); //Max number of messages to be stored while offline
        mDisconnectOption.setPersistBuffer(true); //Save the message into disk not memory

        return mDisconnectOption;
    }

    private MqttConnectOptions getConnectionOption() {

        MqttConnectOptions mConnectionOption = new MqttConnectOptions();
        mConnectionOption.setCleanSession(false); //if true all subscriptions will be lost in the next connection
        mConnectionOption.setAutomaticReconnect(true); //to reconnect after losing the connection

        return mConnectionOption;
    }
}