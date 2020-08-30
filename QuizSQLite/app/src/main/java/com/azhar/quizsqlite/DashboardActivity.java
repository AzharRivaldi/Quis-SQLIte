package com.azhar.quizsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.azhar.quizsqlite.R;
import com.azhar.quizsqlite.CategoryAdapter;
import com.azhar.quizsqlite.CategoryItem;
import com.azhar.quizsqlite.Constant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Azhar Rivaldi on 10/07/2019.
 */

public class DashboardActivity extends AppCompatActivity {

    private GridView mGridView;
    private CategoryAdapter mCategoryAdapter;
    private ArrayList<CategoryItem> mCategoryItems;
    private int[] mColors;
    private String[] mCategoryTitles;
    private String[] mCategoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        setUpCategoryItems();

        mGridView = findViewById(R.id.categories_list);
        mCategoryAdapter = new CategoryAdapter(this, R.layout.grid_view_item, mCategoryItems);
        mGridView.setAdapter(mCategoryAdapter);

        FloatingActionButton fabShare = findViewById(R.id.fab_share);
        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "bagikan dengan :"));
            }
        });

    }

    private void setUpCategoryItems() {
        mCategoryItems = new ArrayList<>();
        mCategoryTitles = getResources().getStringArray(R.array.category_title);
        mCategoryID = getResources().getStringArray(R.array.category_ID);

        mColors = getResources().getIntArray(R.array.colors);

        for (int i = 0; i < mCategoryTitles.length; i++) {
            mCategoryItems.add(new CategoryItem(mColors[i], mCategoryTitles[i], mCategoryID[i]));
            Log.d("TAG", "Title\t" + mCategoryTitles[i] + "\tID\t" + mCategoryID[i]);
        }
    }
}
