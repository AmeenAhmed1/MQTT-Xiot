package com.example.ameen.xiot_internship_assignment.UI;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ameen.xiot_internship_assignment.R;
import com.example.ameen.xiot_internship_assignment.Service.MqttUtil;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.UnsupportedEncodingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PublishFragment extends Fragment {

    @BindView(R.id.radioButtonGroup)
    RadioGroup radioButtonGroup;
    @BindView(R.id.ETTopic)
    EditText ETTopic;
    @BindView(R.id.btnPublish)
    Button btnPublish;

    RadioButton mRadioButton; //The use the selected button with

    private MqttAndroidClient mClient; //Client Object

   View mView;

    public PublishFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Getting the client from the connection [Current Client]
        mClient = UserPanelActivity.mClient;
                //((UserPanelActivity)getActivity()).getmClient();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.publish_fragment, container, false);
        ButterKnife.bind(this, mView);

        return mView;
    }

    @OnClick(R.id.btnPublish)
    public void onViewClicked() {

        MqttUtil mQttUtil = new MqttUtil(); //Object to calla functions

        int selectedRadioButtonId = radioButtonGroup.getCheckedRadioButtonId(); //Current checked button
        mRadioButton = mView.findViewById(selectedRadioButtonId); //selected id for the button

        String mSubscribeTopic = ETTopic.getText().toString(); //Topic to publish to
        try {

            //Publish a msg to a topic
            mQttUtil.publishMessage(mClient, mRadioButton.getText().toString(), 1, mSubscribeTopic);

            Toast.makeText(getActivity(), "Published : *" + mRadioButton.getText().toString() +"*"
                    + " To a topic : *" + mSubscribeTopic +"*", Toast.LENGTH_SHORT).show();

        } catch (MqttException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
