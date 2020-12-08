package com.example.tutoringshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tutoringshop.db.AppDatabase;
import com.example.tutoringshop.db.TutoringShopDAO;

public class LandingPage extends AppCompatActivity {

    private static final String PREFERENCES_KEY = "com.example.tutoringshop.PREFERENCES_Key";

    private static final String USER_ID_KEY = "com.example.tutoringshop.userIdKey";

    private TutoringShopDAO mTutoringShopDAO;

    Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        getDatabase();
        admin = findViewById(R.id.buttonAdmin);
        setAdminBtnVisable();
    }


    private void getDatabase(){
        mTutoringShopDAO = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getTutoringShopDAO();
    }


    public static Intent intentFactory(Context context, int userId){
        Intent intent = new Intent(context, LandingPage.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }


    public void logOut(View view) {
//        SharedPreferences preferences = getSharedPreferences(PREFERENCES_KEY,Context.MODE_PRIVATE);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        finish();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    public void setAdminBtnVisable(){
        User currenUser = mTutoringShopDAO.getUserByUserId(getIntent().getIntExtra(USER_ID_KEY,-1));
        int isAdm = currenUser.getAdmin();
        if(isAdm > 0){
            admin.setVisibility(View.VISIBLE);
        }
        admin.setVisibility(View.INVISIBLE);
    }
}