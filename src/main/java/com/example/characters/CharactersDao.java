package com.example.characters;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CharactersDao {

    @Insert
    public void addToDatabase(Character character);

    @Delete
    public void deleteChatacterFromDatabase(Character character);

    @Update
    public void updateChatacterInDatabase(Character character);

    @Query("Select * from Characters")
    public List<Character> selectAllCharactersInDatabase();

    @Query("Select name from Characters where DateOfFirstConvene> :dateOfFirstConvene")
    public List<String> selectNameOffCharacterWithDate(int dateOfFirstConvene);
}
