package com.boss.escroway.data.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Subcategory {

    @PrimaryKey
    public long _id;
    public long catId;
    public String name;

}
