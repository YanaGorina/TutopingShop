 package com.example.tutoringshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tutoringshop.db.AppDatabase;
import com.example.tutoringshop.db.TutoringShopDAO;

import java.util.List;

 public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_KEY = "com.example.tutoringshop.PREFERENCES_Key";
    private int mUserId = -1;
    private static final String USER_ID_KEY = "com.example.tutoringshop.userIdKey";
    private TutoringShopDAO mTutoringShopDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDatabase();

        checkForUser();
    }


     private void getDatabase(){
         mTutoringShopDAO = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.DB_NAME)
                 .allowMainThreadQueries()
                 .build()
                 .getTutoringShopDAO();
     }


    private void checkForUser() {
        //do we have a user in the intent?
        mUserId = getIntent().getIntExtra(USER_ID_KEY,-1);
        if(mUserId != -1){
            return;
        }
        //do we have a user in preferences?
        SharedPreferences preferences = this.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE);
        mUserId = preferences.getInt(USER_ID_KEY,-1);
        if(mUserId !=-1){
            return;
        }
        //do we have any users at all
        List<User> users = mTutoringShopDAO.getAllUsers();
        if(users.size() <= 0){
            User defaultUser = new User("testuser1","testuser1",0);
            mTutoringShopDAO.insert(defaultUser);
            User defaultAdmin = new User("admin2", "admin2",1);
            mTutoringShopDAO.insert(defaultAdmin);
        }

    }


    public void startLogin(View view){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }


    public void startSignUp (View view){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);

    }


    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }

}