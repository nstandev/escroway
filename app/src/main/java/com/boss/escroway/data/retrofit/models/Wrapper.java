package com.boss.escroway.data.retrofit.models;

import com.boss.escroway.data.retrofit.models.Categories;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wrapper {

    @SerializedName("dealRelationships")
    @Expose
    private List<String> dealRelationships = null;
    @SerializedName("productType")
    @Expose
    private List<String> productType = null;
    @SerializedName("categories")
    @Expose
    private Categories categories;

    public List<String> getDealRelationships() {
        return dealRelationships;
    }

    public void setDealRelationships(List<String> dealRelationships) {
        this.dealRelationships = dealRelationships;
    }

    public List<String> getProductType() {
        return productType;
    }

    public void setProductType(List<String> productType) {
        this.productType = productType;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

}
