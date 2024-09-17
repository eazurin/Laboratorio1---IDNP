package com.example.appregistration;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appregistration.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final String FILE_NAME = "user_data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*       EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        EditText edtName = binding.edtName;
        EditText edtLastname = binding.edtLastname;
        EditText edtEmail = binding.edtEmail;
        EditText edtPhone = binding.edtPhone;
        EditText edtBloodGroup = binding.edtBloodGroup;
        EditText edtAdress = binding.editAddress;
        Button btnRegistration = binding.btnRegistration;
        Button btnView = binding.btnView;

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String lastname = edtLastname.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String bloodGroup = edtBloodGroup.getText().toString();
                String address = edtAdress.getText().toString();
                String userData = "Name: " + name + "\n" +
                        "Lastname: " + lastname + "\n" +
                        "Email: " + email + "\n" +
                        "Phone: " + phone + "\n" +
                        "Blood Group: " + bloodGroup + "\n" +
                        "Address: " + address + "\n\n";


                try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND)) {
                    fos.write(userData.getBytes());
                    Toast.makeText(getApplicationContext(), "Datos guardados", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(TAG, "Error: ", e);
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (FileInputStream fis = openFileInput(FILE_NAME);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                    String line;
                    StringBuilder fileContent = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        fileContent.append(line).append("\n");
                    }
                    Log.d(TAG, "Data:\n" + fileContent.toString());
                    Toast.makeText(getApplicationContext(), "Datos cargados", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.e(TAG, "Error: ", e);
                }
            }
        });
    }
}
