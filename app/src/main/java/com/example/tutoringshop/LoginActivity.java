package com.example.tutoringshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutoringshop.db.AppDatabase;
import com.example.tutoringshop.db.TutoringShopDAO;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private static final String USER_ID_KEY ="com.example.tutoringshop.userIdKey" ;
    private EditText mUserName;
    private EditText mPassword;
    private TutoringShopDAO mTutoringShopDAO;
    private String userName;
    private String password;
    public static String loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserName = findViewById(R.id.editTextUserName);
        mPassword = findViewById(R.id.editTextPassword);
        //wireUpDisplay();

        getDatabase();

        //checkForUser();
    }

    /*private void wireUpDisplay(){
        mUserName = findViewById(R.id.editTextUserName);
        mPassword = findViewById(R.id.editTextPassword);
    }*/

    public void login(View view){
        checkCredential();
        existanceInDatabase();
    }

    private void getDatabase(){
        mTutoringShopDAO = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getTutoringShopDAO();
    }
    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    public void checkCredential(){
         userName  = mUserName.getText().toString();
         password  = mPassword.getText().toString();

        if(userName.equals("")){
            Toast.makeText(this, "Enter your name",Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.equals("")){
            Toast.makeText(this, "Enter your password",Toast.LENGTH_SHORT).show();
            return;
        }

    }
    public void existanceInDatabase(){
        List <User>users = mTutoringShopDAO.getAllUsers();
         for(User user: users){
            if( user.getUserName().equals(userName) && user.getPassword().equals(password)){
                loggedUser = userName;
                Intent intent = LandingPage.intentFactory(this, user.getUserId()); // I will go to the Landing page and I will pass the UserId
                startActivity(intent);

            }
         }
         Toast.makeText(this, "You are not Registered",Toast.LENGTH_SHORT).show();
    }
     public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }


}