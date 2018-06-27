package com.example.ameen.xiot_internship_assignment.UI;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ameen.xiot_internship_assignment.R;

public class MessageFragment extends Fragment {

    private static TextView mTopic, mMessage;

    private static final String TAG = "MessageFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.message_fragment, container, false);

        mTopic = mView.findViewById(R.id.TvComeTopic);
        mMessage = mView.findViewById(R.id.TvComeMessage);

        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    public static void newMessage(String topic, String message) {
        Log.i(TAG, "newMessage: ");

        mTopic.setText(topic);
        mMessage.setText(message);
    }

}
