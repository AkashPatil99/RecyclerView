package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recycler_view;
    private UserAdapter userAdapter;
    private List<UserModel> userList;

    static String myJSONStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myJSONStr = loadJSONFromRaw();

        recycler_view = findViewById(R.id.recycler_view);
//        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setLayoutManager(new GridLayoutManager(this,1));

        recycler_view.setNestedScrollingEnabled(false);
        userAdapter = new UserAdapter(this, prepareData());
        recycler_view.setAdapter(userAdapter);

        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    private List<UserModel> prepareData() {
        userList = new ArrayList<>();
        try {
            JSONArray iJSONArray = new JSONArray(myJSONStr);
            for (int i = 0; i < iJSONArray.length(); i++) {

                JSONObject jsonObject = iJSONArray.getJSONObject(i);
                UserModel userModel = new UserModel(jsonObject.getString("name"),
                        jsonObject.getString("location"),
                        Integer.toString(jsonObject.getInt("followers")),
                        Integer.toString(jsonObject.getInt("contributions")));

                userList.add(userModel);

            }
            return userList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String loadJSONFromRaw() {
        String json;
        try
        {
            InputStream is = getResources().openRawResource(R.raw.githubusersstats);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}