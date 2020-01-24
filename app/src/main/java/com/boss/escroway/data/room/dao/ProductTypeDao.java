package com.boss.escroway.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.boss.escroway.data.room.entities.ProductType;
import com.boss.escroway.data.room.entities.User;

import java.util.List;

@Dao
public interface ProductTypeDao {
    @Query("SELECT * FROM ProductType")
    public List<ProductType> getAllProductTypes();

    @Insert
    public void insertProductypes(ProductType... productTypes);

    @Delete
    public void deleteProductType(ProductType... productTypes);
}
