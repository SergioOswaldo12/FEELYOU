package com.example.feelyou;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class crearCuenta extends AppCompatActivity {

    private Button obtnAtrasC, obtnSingIn, obtnSuscribirse;
    private EditText oedtNombre, oedtApellidos, oetteEmail1, oettpPassword;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //Variables de los datos que vamos a registrar
    private String nombre="";
    private String apellidos="";
    private String email="";
    private String password="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_cuenta);

        TextView miTexto = findViewById(R.id.textView8);
        miTexto.setPaintFlags(miTexto.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        obtnAtrasC = findViewById(R.id.btnAtrasC);

        obtnAtrasC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(crearCuenta.this, MainActivity.class);
                startActivity(intent);
            }
        });

        obtnSingIn = findViewById(R.id.btnSingIn);

        obtnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(crearCuenta.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        oedtNombre = findViewById(R.id.edtNombre);
        oedtApellidos = findViewById(R.id.edtApellidos);
        oetteEmail1 = findViewById(R.id.etteEmail1);
        oettpPassword = findViewById(R.id.ettpPassword);
        obtnSuscribirse = findViewById(R.id.btnSuscribirse);

        obtnSuscribirse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                   nombre = oedtNombre.getText().toString();
                   apellidos = oedtApellidos.getText().toString();
                   email = oetteEmail1.getText().toString();
                   password = oettpPassword.getText().toString();

                   if(!nombre.isEmpty() && !apellidos.isEmpty() && !email.isEmpty() && !password.isEmpty()) {

                       if (password.length() >=8) {
                           registerUser();
                       }
                       else{
                           Toast.makeText(crearCuenta.this,"El password debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                       }
                   }
                   else{
                       Toast.makeText(crearCuenta.this,"Debe completar los campos", Toast.LENGTH_SHORT).show();
                   }
            }
        });

    }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Map<String, Object>map = new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("apellidos", apellidos);
                    map.put("email", email);
                    map.put("password", password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()){
                                startActivity(new Intent(crearCuenta.this, MainActivity.class));
                                finish();
                            }else{
                                Toast.makeText(crearCuenta.this, "Nose pudieron crear los datos correctos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(crearCuenta.this,"No se pudo registrar este Usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}