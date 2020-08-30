package com.azhar.quizsqlite;

/**
 * Created by Azhar Rivaldi on 10/07/2019.
 */

public class CategoryItem {

    private int mBgColor;
    private String mCategoryTitle;
    private String mCategoryID;

    public CategoryItem(int imageId, String categoryTitle, String categoryID) {
        this.mBgColor = imageId;
        this.mCategoryTitle = categoryTitle;
        this.mCategoryID = categoryID;
    }

    public int getmBgColor() {
        return mBgColor;
    }

    public String getmCategoryTitle() {
        return mCategoryTitle;
    }

    public String getmCategoryID() {
        return mCategoryID;
    }
}
