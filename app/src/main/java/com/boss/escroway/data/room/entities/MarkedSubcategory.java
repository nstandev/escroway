package com.boss.escroway.data.room.entities;

import androidx.room.Entity;

@Entity(primaryKeys = {"_id,", "subcatId", "userId"})
public class MarkedSubcategory {
    public long _id;
    public long subcatId;
    public long userId;
}
