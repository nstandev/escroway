package com.boss.escroway.data.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey
    public long _id;
    public long productTypeId;
    public String name;

}
