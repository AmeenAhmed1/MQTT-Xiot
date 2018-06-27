package com.example.ameen.xiot_internship_assignment.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.ameen.xiot_internship_assignment.Model.ClientData;
import com.example.ameen.xiot_internship_assignment.Service.MessageService;
import com.example.ameen.xiot_internship_assignment.Service.MqttUtil;

import com.example.ameen.xiot_internship_assignment.Adapter.PageAdapter;
import com.example.ameen.xiot_internship_assignment.R;

import org.eclipse.paho.android.service.MqttAndroidClient;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserPanelActivity extends AppCompatActivity {

    private static final String TAG = "UserPanelActivity";

    @BindView(R.id.tabPager)
    TabLayout tabPager;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static MqttAndroidClient mClient;
    private MqttUtil mUtils;

    //Getting current client
    /*public MqttAndroidClient getmClient() {
        return mClient;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_panel);
        ButterKnife.bind(this);


        //initialize a service connection object and start the connection
        //Connection mConnectionObj = new Connection();
        mUtils = new MqttUtil();
        mClient = mUtils.makeConnection(this, ClientData.mBroker, ClientData.mClientId);

        Log.i(TAG, "onCreate: UserPanel  " + mClient);

        setupViewPager(viewPager); //Setting up the pages

        tabPager.setupWithViewPager(viewPager); //Setting the pages

        //To start the message service
        Intent intent = new Intent(UserPanelActivity.this, MessageService.class);
        startService(intent);
    }

    public Context getCurrentContext(){
        return this.getApplicationContext();
    }
    //Setting up the pager tabs
    private void setupViewPager(ViewPager viewPager) {
        PageAdapter mAdapter = new PageAdapter(getSupportFragmentManager());
        mAdapter.addNewFragment(new MessageFragment(), "Messages");
        mAdapter.addNewFragment(new PublishFragment(), "Publish");
        mAdapter.addNewFragment(new SubscribeFragment(), "Subscribe");
        viewPager.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClient.unregisterResources();
        mClient.close();
    }
}
