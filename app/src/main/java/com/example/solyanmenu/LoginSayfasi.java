package com.example.solyanmenu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginSayfasi extends AppCompatActivity {

    private EditText emailEdittext,parolaEdittext;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sayfasi);

        emailEdittext=(EditText)findViewById(R.id.email_edittext);
        parolaEdittext=(EditText)findViewById(R.id.parola_edittext);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void anasayfayaGit(View view) {

        String email=emailEdittext.getText().toString();
        String parola=parolaEdittext.getText().toString();

        if(email.isEmpty()){
            emailEdittext.setError("Email gerekli");
            emailEdittext.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdittext.setError("Geçerli bir mail adresi gerekli");
            emailEdittext.requestFocus();
            return;
        }
        if(parola.isEmpty()){
            parolaEdittext.setError("Parola gerekli!");
            parolaEdittext.requestFocus();
            return;
        }
        if(parola.length()<6){
            parolaEdittext.setError("Parola 6 karakterden kısa alamaz!");
            parolaEdittext.requestFocus();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,parola).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Başarılı",
                            Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Başarısız :"+task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void kayitSayfasinaGit(View view) {

        Intent i=new Intent(getApplicationContext(),KayitActivity.class);
        startActivity(i);
    }
}
