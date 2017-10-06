package com.blankdev.znote.ui.fragment.create;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.blankdev.znote.R;
import com.blankdev.znote.model.TableItems;
import com.blankdev.znote.provider.RequestProvider;
import com.blankdev.znote.ui.fragment.BaseFragment;
import com.blankdev.znote.ui.fragment.home.HomeFragment;
import com.blankdev.znote.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateNoteFragment extends BaseFragment {

    @BindView(R.id.etJudul)
    EditText etJudul;
    @BindView(R.id.etNote)
    EditText etNote;

    @Override
    protected int init_view() {
        return R.layout.fragment_create_note;
    }

    @Override
    protected void setupViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void run(Bundle savedInstanceState) {

    }
}
