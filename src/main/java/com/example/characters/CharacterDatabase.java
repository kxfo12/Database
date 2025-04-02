package com.example.characters;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Character.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {
    public abstract CharactersDao returnDao();
}
