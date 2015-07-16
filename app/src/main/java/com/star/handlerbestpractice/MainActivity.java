package com.star.handlerbestpractice;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mChangeTextButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new MyHandler();

        mTextView = (TextView) findViewById(R.id.text);

        mChangeTextButton = (Button) findViewById(R.id.change_text);
        mChangeTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bundle bundle = new Bundle();
                        bundle.putString("text", "Nice to meet you");

                        Message message = new Message();
                        message.setData(bundle);

                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            mTextView.setText(bundle.getString("text"));
        }
    }

}
