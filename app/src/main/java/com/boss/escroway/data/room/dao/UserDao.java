package com.boss.escroway.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.boss.escroway.data.room.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAllUsers();

    @Insert
    void insertUser(User... users);

    @Delete
    void deleteUser(User... users);

}
