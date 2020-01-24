package com.boss.escroway.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.boss.escroway.data.room.entities.MarkedSubcategory;
import com.boss.escroway.data.room.entities.Subcategory;

import java.util.List;

@Dao
public interface SubcategoryDao {
    @Query("SELECT * FROM Subcategory")
    public List<Subcategory> getSubcategory();

    @Insert
    public void insertSubcategory(Subcategory... subcategories);

    @Delete
    public void deleteSubcategory(Subcategory... subcategories);
}
