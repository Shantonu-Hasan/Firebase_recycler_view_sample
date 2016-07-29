package com.saeed.ashik.firebaserecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference rootDbRef;
    EditText userNameET;
    EditText rollET;
    Button submitButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootDbRef = FirebaseDatabase.getInstance().getReference();
        userNameET = (EditText) findViewById(R.id.userNameET);
        rollET = (EditText) findViewById(R.id.rollET);
        submitButton = (Button) findViewById(R.id.sumitButton);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        submitButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference ref = rootDbRef.child("input");


        FirebaseRecyclerAdapter<User, MyViewHolder> recyclerAdapter = new FirebaseRecyclerAdapter<User, MyViewHolder>
                (User.class, R.layout.list_item_layout, MyViewHolder.class, ref) {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, User model, int position) {
                    viewHolder.userNameET.setText(model.getUserName());
                    viewHolder.rollET.setText(model.getRoll());
            }
        };
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.sumitButton) {
            String userName = userNameET.getText().toString();
            String roll = rollET.getText().toString();
            User user = new User(userName,roll);

            rootDbRef.child("input").push().setValue(user);
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView userNameET;
        TextView rollET;

        public MyViewHolder(View itemView) {
            super(itemView);

            userNameET = (TextView) itemView.findViewById(R.id.listUserNameET);
            rollET = (TextView) itemView.findViewById(R.id.listRollET);
        }
    }
}
