package com.example.firebase_connection;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class SaveDataActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private EditText editTextData;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        db = FirebaseFirestore.getInstance();
        editTextData = findViewById(R.id.editTextData);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String text = editTextData.getText().toString().trim();

        if (!text.isEmpty()) {
            Map<String, Object> data = new HashMap<>();
            data.put("message", text);

            db.collection("messages")
                    .add(data)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(SaveDataActivity.this, "Data Saved!", Toast.LENGTH_SHORT).show();
                        editTextData.setText("");
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(SaveDataActivity.this, "Error saving data", Toast.LENGTH_SHORT).show();
                        Log.e("Firestore", "Error adding document", e);
                    });
        } else {
            Toast.makeText(this, "Please enter some data", Toast.LENGTH_SHORT).show();
        }
    }
}
