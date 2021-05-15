package com.kawka.arbitrajlibrary.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kawka.arbitrajlibrary.R;
import com.kawka.arbitrajlibrary.UserData;
import com.kawka.arbitrajlibrary.fb.FBCore;

public class FEvents {

    private static final String TAG = FEvents.class.getName();
    private DatabaseReference mDatabase;

    public void checkEvents(Context context){
        Log.d(TAG, "checkEvents_start");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String ch1 = context.getPackageName().replace(".", "")+"/events";
        String ch2 = new UserData(context).getUserData(UserData.PREF_USER_KEY);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();


                        Log.d(TAG, token);
                        //Toast.makeText(context, token, Toast.LENGTH_SHORT).show();
                    }
                });


        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                String post = dataSnapshot.getValue(String.class);
                String d = "";
                if(post != null ) new FBCore(context).initEventsReg();
                mDatabase.removeValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.child(ch1).child(ch2).addValueEventListener(postListener);
    }


}
