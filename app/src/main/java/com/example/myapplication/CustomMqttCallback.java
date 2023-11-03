package com.example.myapplication;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import android.widget.Toast;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import android.content.Context;

public class CustomMqttCallback implements MqttCallback {
    private Context context;

    public CustomMqttCallback(Context context) {
        this.context = context;
    }

    @Override
    public void connectionLost(Throwable cause) {
        // Handle connection loss (or provide an empty implementation)
        // For example, you can show a Toast message indicating the connection loss
        Toast.makeText(context, "Connection Lost", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // Handle the received message here
        String payload = new String(message.getPayload());

        // Display the received message using a Toast
        Toast.makeText(context, "Received Message on " + topic + ": " + payload, Toast.LENGTH_SHORT).show();

        // You can also update a TextView or perform any other action with the received message
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Handle message delivery completion (optional)
        // This method is called when a message has been successfully delivered
        // You can provide an empty implementation if you don't need to take specific action here
    }
}
