package com.boss.escroway.data.room.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class SubcategoryWithCategory {
    @Embedded public Category mCategory;
    @Relation(
            parentColumn = "_id",
            entityColumn = "catId"
    )
    public List<Subcategory> mSubcategories;
}
