package com.example.mealstodaymess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MessMenuUpdate extends AppCompatActivity {

    private DatabaseReference mDatabase;
    FirebaseDatabase firebase;
    TextView t1,t6;
    EditText s1,s2,d1,f1,dal1,amt1;
    CheckBox c1,c2;
    Button updt;
    String sb1,sb2,des1,fst1,messid1,dalstr,amtstr;
    Boolean rt1,rc1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_menu_update);
        String username=Transfertext.emailidtransfer;

        messid1=Transfertext.emailidtransfer;

        t1=(TextView) findViewById(R.id.textView);
        t6=(TextView) findViewById(R.id.textView6);
        t1.setText(username);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        firebase=FirebaseDatabase.getInstance();



        s1=(EditText) findViewById(R.id.editText3);
        s2=(EditText) findViewById(R.id.editText4);
        d1=(EditText) findViewById(R.id.editText5);
        f1=(EditText) findViewById(R.id.editText6);
        dal1=(EditText) findViewById(R.id.editText7);
        amt1=(EditText) findViewById(R.id.editText8);

        c1=(CheckBox) findViewById(R.id.cb1);
        c2=(CheckBox) findViewById(R.id.cb2);

        updt=(Button) findViewById(R.id.button1);

        updt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sb1=s1.getText().toString().trim();
                sb2=s2.getText().toString().trim();
                fst1=f1.getText().toString().trim();
                des1=d1.getText().toString().trim();
                dalstr=dal1.getText().toString().trim();
                amtstr=amt1.getText().toString().trim();


                if(c1.isChecked())
                {
                    rt1=true;
                }
                else
                {
                    rt1=false;
                }

                if(c2.isChecked())
                {
                    rc1=true;
                }
                else
                {
                    rc1=false;
                }


                final String s11;
                s11=messid1.substring(0,messid1.length()-10);
                DatabaseReference data1 = firebase.getReference("MESSMAP");

                data1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                       String areak =dataSnapshot.child(s11).getValue().toString();

                       t6.setText(areak);

                       try
                        {
                            DatabaseReference data = firebase.getReference("MESS");
                            data.child(areak).child(s11).child("MENU").child("SABZI1").setValue(sb1);
                            data.child(areak).child(s11).child("MENU").child("SABZI2").setValue(sb2);
                            data.child(areak).child(s11).child("MENU").child("FEAST").setValue(fst1);
                            data.child(areak).child(s11).child("MENU").child("DESSERT").setValue(des1);
                            data.child(areak).child(s11).child("MENU").child("ROTI").setValue(rt1);
                            data.child(areak).child(s11).child("MENU").child("RICE").setValue(rc1);
                            data.child(areak).child(s11).child("MENU").child("DAL").setValue(dalstr);
                            data.child(areak).child(s11).child("MENU").child("AMOUNT").setValue(amtstr);

                        }
                      catch (Exception e)
                        {

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

               /* try
                {
                    DatabaseReference data = firebase.getReference("MESS");
                    data.child(s11).child("SABZI1").setValue(sb1);
                    data.child(s11).child("SABZI2").setValue(sb2);
                    data.child(s11).child("FEAST").setValue(fst1);
                    data.child(s11).child("DESSERT").setValue(des1);
                    data.child(s11).child("ROTI").setValue(rt1);
                    data.child(s11).child("RICE").setValue(rc1);

                }
                catch (Exception e)
                {

                }*/


            }
        });

    }
}
