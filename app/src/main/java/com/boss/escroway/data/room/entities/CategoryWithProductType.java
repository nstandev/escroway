package com.boss.escroway.data.room.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryWithProductType {
    @Embedded public ProductType mProductType;
    @Relation(
            parentColumn = "_id",
            entityColumn = "productTypeId"
    )
    public List<Category> mCategories;
}
