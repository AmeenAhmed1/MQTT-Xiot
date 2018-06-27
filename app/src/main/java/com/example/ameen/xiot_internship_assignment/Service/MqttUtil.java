package com.example.ameen.xiot_internship_assignment.Service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MqttUtil{

    private static final String TAG = "MqttUtil";

    MqttAndroidClient mClient;

    public MqttUtil() { }

    //Subscribe function
    public void subscribe(@NonNull MqttAndroidClient client, @NonNull final String topic, int qos) throws MqttException {

        IMqttToken token = client.subscribe(topic, qos); //Subscribe to a topic
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                //Toast.makeText(context, "Subscribe to " + topic + "completed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Subscribe Successfully " + topic);
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                //Toast.makeText(context, "Subscribe to " + topic + "failed", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Subscribe Failed " + topic);

            }
        });
    }


    //Publish message function
    public void publishMessage(@NonNull MqttAndroidClient client, @NonNull String msg, int qos, @NonNull String topic)
            throws MqttException, UnsupportedEncodingException {

        byte[] encodedPayload = new byte[0];
        encodedPayload = msg.getBytes("UTF-8");
        MqttMessage message = new MqttMessage(encodedPayload);
        message.setId(320);
        message.setRetained(true);
        message.setQos(qos);
        client.publish(topic, message); //Publish message to a topic
        //Toast.makeText(context, "Topic has been published", Toast.LENGTH_SHORT).show();
    }


    public MqttAndroidClient makeConnection(final Context context, String brokerUrl, String clientId){
        Connection mConnection = new Connection();
        return mClient = mConnection.makeConnection(context, brokerUrl, clientId);
    }

}
