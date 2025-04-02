package com.example.characters;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Characters")
public class Character {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "characterId")

    private int Id;
    private String name;
    private String description;
    @ColumnInfo(name = "DateOfFirstConvene")
    private int date;
    private String type;
    private String region;

    @Ignore
    public Character() {
    }

    public Character(String name, String description, int date, String type, String region) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.type = type;
        this.region = region;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getRegion() {
        return region;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Nazwa " + name + ", Description: " + description;
    }
}
