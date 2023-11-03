package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.adservices.topics.Topic;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String BROKER_URL = "tcp://127.0.0.1:1883";
    private static final String CLIENT_ID = "your-client-id";
    private MqttHandler mqttHandler;

    CustomMqttCallback mqttCallback = new CustomMqttCallback(this);

    private Button update_ip_button;
    private Button send_message_1_button;
    private Button send_message_2_button;
    private EditText ip_editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mqttHandler = new MqttHandler();
        mqttHandler.connect(BROKER_URL, CLIENT_ID);

        ip_editText = findViewById(R.id.ip_edit_text);
        update_ip_button = findViewById(R.id.ip_button);
        send_message_1_button = findViewById(R.id.topic_1_button);
        send_message_2_button = findViewById(R.id.topic_2_button);


        if (mqttHandler.isConnected()) {
            Toast.makeText(this, "Connected to MQTT broker", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Not connected to MQTT broker", Toast.LENGTH_SHORT).show();
        }

        update_ip_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BROKER_URL = "tcp://" + ip_editText.getText();
            }
        });

        send_message_1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishMsg("topic1", "This is message 1");
            }
        });
        send_message_2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                publishMsg("topic1", "This is message 2");
            }
        });
    }
    @Override
    protected void onDestroy(){
        mqttHandler.disconnect();
        super.onDestroy();
    }

    private void publishMsg(String topic, String msg){
        Toast.makeText(this, "Publishing Message: " + msg, Toast.LENGTH_SHORT).show();
        mqttHandler.publish(topic, msg);
    }

    public void subscribeToTopic(String topic, CustomMqttCallback callback) {
        Toast.makeText(this, "Subscribing to: " + topic, Toast.LENGTH_SHORT).show();
        mqttHandler.subscribe(topic, callback);
    }



}