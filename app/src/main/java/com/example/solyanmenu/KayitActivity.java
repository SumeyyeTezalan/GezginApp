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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class KayitActivity extends AppCompatActivity {

    private EditText emailEdittext,parolaEdittext,parolaTekrarEdittext;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);

        emailEdittext=(EditText)findViewById(R.id.email_edittext);
        parolaEdittext=(EditText)findViewById(R.id.parola_edittext);
        parolaTekrarEdittext=(EditText)findViewById(R.id.parola_tekrar_edittext);

        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void kayitIslemiYap(View view) {
        String email=emailEdittext.getText().toString();
        String parola=parolaEdittext.getText().toString();
        String parolaTekrar=parolaTekrarEdittext.getText().toString();

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
        if(!parola.equals(parolaTekrar)){
            parolaTekrarEdittext.setError("Parola eşleşmedi!");
            parolaTekrarEdittext.requestFocus();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,parola).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Başarılı",
                            Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),LoginSayfasi.class);
                    startActivity(i);
                }else if(task.getException() instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(getApplicationContext(),
                            "Böyle bir kayıt bulunmaktadır!",Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(getApplicationContext(),
                            "Başarısız :"+task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void loginSayfasinaDon(View view) {

        Intent i=new Intent(getApplicationContext(),LoginSayfasi.class);
        startActivity(i);
    }
}
