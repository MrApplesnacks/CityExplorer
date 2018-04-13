package com.a25zsa.firebasetest;

import android.content.Intent;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ValueEventListener {

    EditText user;
    EditText pass;
    Button signIn;
    Button signUp;
    DatabaseReference firebase;
    FirebaseDatabase firebaseData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = (EditText)findViewById(R.id.editText);
        pass = (EditText)findViewById(R.id.editText2);
        signIn = (Button)findViewById(R.id.button2);
        signUp = (Button)findViewById(R.id.button3);

        firebase = FirebaseDatabase.getInstance().getReference("Accounts");
        firebaseData = FirebaseDatabase.getInstance();

        //signIn.setOnClickListener(this);
        //signUp.setOnClickListener(this);
        //firebase.addValueEventListener(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent signUpPage = new Intent(MainActivity.this, SignUp.class);
                startActivity(signUpPage);
            }
        });

        signIn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                //verifyLogIn();
                Intent signInPage = new Intent(MainActivity.this, LogIn.class);
                startActivity(signInPage);
            }
        });

    }

    public void verifyLogIn(){
        Log.d("Firebase", "start verifyLogIn");
        final String checkUser = user.getText().toString().trim();
        final String checkPass = pass.getText().toString().trim();
        final DatabaseReference temp = firebase;

        final ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Firebase", "start onDataChange");

                if (dataSnapshot.hasChild(checkUser)) {
                    Log.d("Firebase", "true");
                    Object value = dataSnapshot.child(checkUser).child("password").getValue();
                    String t = value.toString();

                    if(t.equals(checkPass)){
                        Toast.makeText(getBaseContext(), "Logging in, need next page", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Incorrect name or pass", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Log.d("Firebase", "start onDataChangeElse");
                    Toast.makeText(getBaseContext(), "Incorrect name or pass", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ValueEventListener eventListener = valueEventListener;
        temp.addListenerForSingleValueEvent(eventListener);
        Log.d("Firebase", "start LogInEnd");
    }

    public void verifiyNewUser(){
        final String checkUser = user.getText().toString().trim();
        final String checkPass = pass.getText().toString().trim();

        if(TextUtils.isEmpty(checkUser) || TextUtils.isEmpty(checkPass)){
            Toast.makeText(this, "Missing Field", Toast.LENGTH_SHORT).show();
            return;
        }

        final DatabaseReference temp = firebase.child(checkUser);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    //Log.d("FirebaseTest", "onDataChangeIf" );
                    UserAccount newUser = new UserAccount(checkUser, checkPass);
                    temp.setValue(newUser);
                    Toast.makeText(getBaseContext(), "Sign Up Success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.d("FirebaseTest", "onDataChangeElse" );
                    Toast.makeText(getBaseContext(), "Name Taken", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        temp.addListenerForSingleValueEvent(eventListener);
    }

    public void addData(){
        String userName = user.getText().toString().trim();
        String passWord = pass.getText().toString().trim();

        UserAccount newUser = new UserAccount(userName, passWord);
        firebase.child(userName).setValue(newUser);
        //LogInInformation logIn = new LogInInformation(userName, passWord);
        //firebase.child(logIn.get1User1()).setValue(logIn);
        //firebaseData.getReference().child(userName).setValue(newUser);
        //firebase.push();
    }

    public void onClick(View view){
        if(view == signIn){
            addData();
        }
        if(view == signUp){
            getData();
        }
    }

    public void getData(){
        String test = firebase.child("Named").toString();
        Log.d("FirebaseTest", test );
    }

    public void onCancelled(DatabaseError databaseError){

    }
    public void onDataChange(DataSnapshot dataSnapShot){
        Toast.makeText(this, "something changed", Toast.LENGTH_SHORT).show();
    }





}
