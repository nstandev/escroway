package com.boss.escroway.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.boss.escroway.data.room.entities.MarkedSubcategory;

import java.util.List;

@Dao
public interface MarkedSubcategoryDao {
    @Query("SELECT * FROM MarkedSubcategory")
    public List<MarkedSubcategory> getMarkedSubcategory();

    @Insert
    public void insertMarkedSubcategory(MarkedSubcategory... markedSubcategories);

    @Delete
    public void deleteMarkedSubcategory(MarkedSubcategory... markedSubcategories);
}
