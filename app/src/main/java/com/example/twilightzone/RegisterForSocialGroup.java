package com.example.twilightzone;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twilightzone.buissinessentities.PublicRegEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterForSocialGroup extends AppCompatActivity {
/*
* user id
* password
* name
* address
* contact no
* email
* */

    String name,password,address,contact,email,userid;
    EditText ename,epassword,eaddress,econtact,eemail,euserid;
    Button submit;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users/socialgroups");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_for_social_group);
        ename = (EditText)findViewById(R.id.nameforsoc);
        epassword = (EditText)findViewById(R.id.passwordforsoc);
        eaddress = (EditText)findViewById(R.id.addressforsoc);
        econtact = (EditText)findViewById(R.id.phonenoforsoc);
        eemail = (EditText)findViewById(R.id.emailidforsoc);
        euserid = (EditText)findViewById(R.id.useridforsoc);
        submit = (Button)findViewById(R.id.submitforsoc);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = ename.getText().toString();
                password = epassword.getText().toString();
                address = eaddress.getText().toString();
                contact = econtact.getText().toString();
                email = eemail.getText().toString();
                userid = euserid.getText().toString();

                checkdabase(name,password,address,contact,email,userid);

            }
        });



    }



    public void checkdabase(final String name, final String password, final String address, final String contact, final String email, final String userid){

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(userid)){
                    Toast.makeText(RegisterForSocialGroup.this,"You cant create your account using this userid",Toast.LENGTH_LONG).show();


                }
                else{
                    PublicRegEntity publicRegEntity = new PublicRegEntity(password,name,address,contact,email);
                    myRef.child(userid).setValue(publicRegEntity);
                    Toast.makeText(RegisterForSocialGroup.this,"Your account have been created",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
