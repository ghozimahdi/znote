package com.blankdev.znote.ui.fragment.home;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blankdev.znote.R;
import com.blankdev.znote.util.CursorRecyclerViewAdapter;

/**
 * Created by knalb on 14/07/17.
 */

public class CustomCursorRecyclerViewAdapter extends CursorRecyclerViewAdapter {

    private final int VIEW_TYPE_ITEM = 0;

    public CustomCursorRecyclerViewAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);
            return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
            CustomViewHolder holder = (CustomViewHolder) viewHolder;
            cursor.moveToPosition(cursor.getPosition());
            holder.setData(cursor);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_ITEM;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
