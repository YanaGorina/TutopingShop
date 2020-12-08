package com.example.tutoringshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutoringshop.db.AppDatabase;
import com.example.tutoringshop.db.TutoringShopDAO;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private static final String USER_ID_KEY ="com.example.tutoringshop.userIdKey" ;
    private EditText mUserName;
    private EditText mPassword;
    private TutoringShopDAO mTutoringShopDAO;
    private String userName;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toast.makeText(this, "On create", Toast.LENGTH_SHORT).show();


        mUserName = findViewById(R.id.editTextSignUserName);
        mPassword = findViewById(R.id.editTextSignPassword);
        getDatabase();
    }


    private void getDatabase(){
        mTutoringShopDAO = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getTutoringShopDAO();
    }


    public void signUp(View view){
        if(checkCredential()){
            existanceInDatabase();
        }
    }


    public boolean checkCredential() {
        userName = mUserName.getText().toString();
        password = mPassword.getText().toString();

        if (userName.equals("")) {
            Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.equals("")) {
            Toast.makeText(this, "Enter your password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void existanceInDatabase(){

        Toast.makeText(this, "In existanceInDatabase", Toast.LENGTH_SHORT).show();


        List<User> users = mTutoringShopDAO.getAllUsers();
        for(User user: users){
            if( user.getUserName().equals(userName) && user.getPassword().equals(password)){
                Toast.makeText(this, "You are already registered",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        User newUser = new User(userName,password,0);
        mTutoringShopDAO.insert(newUser);
        Toast.makeText(this, "You are signed up",Toast.LENGTH_SHORT).show();

        Intent intent = LandingPage.intentFactory(this, newUser.getUserId());
        startActivity(intent);

    }

}