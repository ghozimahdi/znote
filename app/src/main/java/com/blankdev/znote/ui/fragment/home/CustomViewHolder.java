package com.blankdev.znote.ui.fragment.home;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankdev.znote.R;
import com.blankdev.znote.model.TableItems;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by knalb on 14/07/17.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tvTitle) TextView tvTitle;

    public CustomViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(Cursor c) {
        tvTitle.setText(c.getString(c.getColumnIndex(TableItems.TITLE)));
    }
}
