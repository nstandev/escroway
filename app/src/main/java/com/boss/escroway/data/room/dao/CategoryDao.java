package com.boss.escroway.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.boss.escroway.data.room.entities.Category;
import com.boss.escroway.data.room.entities.User;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("SELECT * FROM Category")
    public List<Category> getAllCategories();

    @Insert
    public void insertCategory(Category... categories);

    @Delete
    public void deleteCategory(Category... categories);
}
