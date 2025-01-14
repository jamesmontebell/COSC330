package com.example.networking;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;


public class MainActivity extends AppCompatActivity {
    EditText message;
    TextView sendMessage;
    TextView actAsServer;

    private BluetoothAdapter ba;
    private Set<BluetoothDevice> paired;
    private BluetoothDevice otherDevice;
    private BluetoothSocket socket;
    private BluetoothServerSocket server;
    final Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.message);
        sendMessage = findViewById(R.id.connect);
        actAsServer = findViewById(R.id.listening);

        ba = BluetoothAdapter.getDefaultAdapter();
    }

    public void send(View v)
    {
        if (ba.isEnabled()) {
            return;
        } else {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            if (!ba.isDiscovering()) {
                ba.startDiscovery();
                paired = ba.getBondedDevices();
                for(BluetoothDevice a:paired)
                {
                    if(a.getName().toString().equals("Pixel XL API 34"));
                    otherDevice = a;
                    break;
                }
            }
            ba.cancelDiscovery();
            try {
                socket = otherDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00000000-0000-0000-0000-000000000001"));
                if(socket.isConnected())
                    socket.connect();
                OutputStream out = socket.getOutputStream();
                String mess =  message.getText().toString();
                byte data[] = mess.getBytes();
                out.write(data, 0 , data.length);
                out.flush();
                message.setText("");
                Thread.sleep(1000);
                socket.close();



            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void server(View v)
    {
        if (ba.isEnabled()) {
            return;
        } else {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            try {
                server = ba.listenUsingInsecureRfcommWithServiceRecord("small app",UUID.fromString("00000000-0000-0000-0000-000000000001"));
                socket = server.accept();

                InputStream in = socket.getInputStream();
                byte data[] = new byte[1024];
                in.read(data, 0 , data.length);
                message.setText(new String(data, "UTF-8"));
                Thread.sleep(1000);
                socket.close();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
