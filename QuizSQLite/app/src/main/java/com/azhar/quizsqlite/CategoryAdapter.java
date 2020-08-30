package com.azhar.quizsqlite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.quizsqlite.CategoryItem;
import com.azhar.quizsqlite.QuestionActivity;
import com.azhar.quizsqlite.R;

import java.util.ArrayList;

/**
 * Created by Azhar Rivaldi on 10/07/2019.
 */

public class CategoryAdapter extends ArrayAdapter<CategoryItem> {

    public static final String CATEGORY_COLOR = "CategoryColor";
    public static final String CATEGORY_ID = "CategoryID";
    private Context mContext;
    private ArrayList<CategoryItem> mCategoryItems;

    public CategoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CategoryItem> categoryItems) {
        super(context, resource, categoryItems);
        this.mContext = context;
        this.mCategoryItems = categoryItems;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        CategoryViewHolder categoryViewHolder;
        final CategoryItem categoryItem = mCategoryItems.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_view_item, parent, false);
            categoryViewHolder = new CategoryViewHolder(convertView);
            convertView.setTag(categoryViewHolder);
        }
        categoryViewHolder = (CategoryViewHolder) convertView.getTag();
        categoryViewHolder.categoryImage.setBackgroundColor(categoryItem.getmBgColor());
        categoryViewHolder.categoryTitle.setText(categoryItem.getmCategoryTitle());
        categoryViewHolder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent questionIntent = new Intent(mContext, QuestionActivity.class);
                questionIntent.putExtra(CATEGORY_ID, categoryItem.getmCategoryID());
                questionIntent.putExtra(CATEGORY_COLOR, categoryItem.getmBgColor());
                mContext.startActivity(questionIntent);
            }
        });
        return convertView;
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView categoryTitle;

        CategoryViewHolder(View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.category_image);
            categoryTitle = itemView.findViewById(R.id.category_title);
        }
    }
}
