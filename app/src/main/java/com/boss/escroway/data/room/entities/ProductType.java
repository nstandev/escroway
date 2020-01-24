package com.boss.escroway.data.room.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProductType {

    @PrimaryKey
    public long _id;
    public String name;
}
