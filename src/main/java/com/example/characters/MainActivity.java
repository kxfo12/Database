package com.example.characters;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.hardware.lights.LightState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.characters.databinding.ActivityMainBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private CharacterDatabase characterDatabase;
    private ArrayAdapter<Character> arrayAdapter;
    private List<Character> character;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };
        characterDatabase = Room.databaseBuilder(
                getApplicationContext(),
                CharacterDatabase.class,
                "characterDatabase"
        ).addCallback(myCallBack).allowMainThreadQueries().build();

        selectCharacterFromDatabase();

        binding.buttonAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //if(binding.editTextName.getText().toString().equals("") && binding.editTextDescription.getText().toString().equals("") && Integer.parseInt(binding.editTextDate.getText().toString()) != 0 && binding.editTextType.toString().equals("") && binding.editTextRegion.toString().equals("")){
                            addCharacterToDatabase();
                        //}
                    }
                }
        );
        binding.listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        characterDatabase.returnDao().deleteChatacterFromDatabase(character.get(i));
                        character.remove(i);
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
    }
    private void addCharacterToDatabase(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {

                        characterDatabase.returnDao().addToDatabase(new Character(binding.editTextName.getText().toString(), binding.editTextDescription.getText().toString(), Integer.parseInt(binding.editTextDate.getText().toString()), binding.editTextType.getText().toString(), binding.editTextRegion.getText().toString()));

                        //characterDatabase.returnDao().addToDatabase(new Character("Changli", "Pheonix", 2024, "Main DPS", "Mt. Firnament"));
                        //characterDatabase.returnDao().addToDatabase(new Character("Lingyang", "Dont play it", 2024, "Main DPS/Troll", "Huanglong"));
                        //characterDatabase.returnDao().addToDatabase(new Character("Phrolova", "Best villian", 2025, "Main DPS", "Rinascita?"));

                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Podziałało lepiej niż AK0", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }
                }
        );
    }
    private void selectCharacterFromDatabase(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(
                new Runnable() {
                    @Override
                    public void run() {

                        character = characterDatabase.returnDao().selectAllCharactersInDatabase();

                        handler.post(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, character);
                                        binding.listView.setAdapter(arrayAdapter);
                                    }
                                }
                        );
                    }
                }
        );
    }
}