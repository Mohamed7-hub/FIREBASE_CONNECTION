package com.example.firebase_connection;
hh
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        Log.d("Firebase", "Firebase is connected successfully!");

        // Find the button and set click listener
        Button btnOpenSaveActivity = findViewById(R.id.btnOpenSaveActivity);
        btnOpenSaveActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open SaveDataActivity
                Intent intent = new Intent(MainActivity.this, SaveDataActivity.class);
                startActivity(intent);
            }
        });
    }
}
