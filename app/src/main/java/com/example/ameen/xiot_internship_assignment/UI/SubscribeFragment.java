package com.example.ameen.xiot_internship_assignment.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ameen.xiot_internship_assignment.R;
import com.example.ameen.xiot_internship_assignment.Service.Connection;
import com.example.ameen.xiot_internship_assignment.Service.MqttUtil;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SubscribeFragment extends Fragment {

    @BindView(R.id.etTopic)
    EditText etTopic;
    @BindView(R.id.btnSubscribe)
    Button btnSubscribe;

    private static final String TAG = "SubscribeFragment";

    MqttAndroidClient mClient; //Client Object

    public SubscribeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: 2");
        super.onCreate(savedInstanceState);

        //Getting the client from the connection [Current Client]
        mClient = UserPanelActivity.mClient;
                //((UserPanelActivity)getActivity()).getmClient();

        Log.i(TAG, "onCreate: 2.1 " + mClient.toString());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: 3");

        View mView = inflater.inflate(R.layout.subscribe_fragment, container, false);
        ButterKnife.bind(this, mView);

        return mView;
    }

    @OnClick(R.id.btnSubscribe)
    public void onViewClicked() {

        Log.i(TAG, "onViewClicked: Client " + mClient.toString());

        MqttUtil mQttUtil = new MqttUtil(); //Object to call a functions

        String mSubscribeTopic = etTopic.getText().toString(); //the topic to subscribe

        try {
            mQttUtil.subscribe(mClient, mSubscribeTopic, 1); //To subscribe to a topic
            Toast.makeText(getActivity(), "Subscribed to " + mSubscribeTopic, Toast.LENGTH_SHORT).show();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
