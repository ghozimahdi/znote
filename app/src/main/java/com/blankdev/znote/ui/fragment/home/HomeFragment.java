package com.blankdev.znote.ui.fragment.home;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.blankdev.znote.R;
import com.blankdev.znote.model.TableItems;
import com.blankdev.znote.provider.RequestProvider;
import com.blankdev.znote.ui.fragment.BaseFragment;
import com.blankdev.znote.ui.fragment.create.CreateNoteFragment;
import com.blankdev.znote.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = "HomeFragment";

    private Handler handlerToWait = new Handler();

    public final int offset = 30;
    private int page = 0;

    private boolean loadingMore = false;
    private Toast shortToast;

    private CustomCursorRecyclerViewAdapter mAdapter;

    @BindView(R.id.rc)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int init_view() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setupViews(View view) {
        ButterKnife.bind(this,view);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        setHasOptionsMenu(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mAdapter = new CustomCursorRecyclerViewAdapter(getActivity(), null);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void run(Bundle savedInstanceState) {
        shortToast = Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0
                        && totalItemCount >= offset) {

                    /*isLoading = true;*/

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingMore = true;
                            page++;
                            getActivity().getSupportLoaderManager().restartLoader(0, null, HomeFragment.this);
                        }
                    }, 2000);
                }
            }
        });

        getActivity().getSupportLoaderManager().restartLoader(0, null, this);
    }

    private void fillMx(Cursor data, MatrixCursor mx) {
        if (data == null)
            return;

        data.moveToPosition(-1);
        while (data.moveToNext()) {
            mx.addRow(new Object[]{
                    data.getString(data.getColumnIndex(TableItems._ID)),
                    data.getString(data.getColumnIndex(TableItems.TITLE)),
                    data.getString(data.getColumnIndex(TableItems.NOTE)),
                    data.getString(data.getColumnIndex(TableItems.DATE))
            });
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case 0:
                return new CursorLoader(getActivity(), RequestProvider.urlForItems(offset * page), null, null, null, null);
            default:
                throw new IllegalArgumentException("no id handled!");
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        switch (loader.getId()) {
            case 0:
                Log.d(TAG, "onLoadFinished: loading MORE");
                /*shortToast.setText("loading MORE " + page);
                shortToast.show();*/

                Cursor cursor = ((CustomCursorRecyclerViewAdapter) mRecyclerView.getAdapter()).getCursor();

                //fill all exisitng in adapter
                MatrixCursor mx = new MatrixCursor(TableItems.Columns);
                fillMx(cursor, mx);

                //fill with additional result
                fillMx(data, mx);

                ((CustomCursorRecyclerViewAdapter) mRecyclerView.getAdapter()).swapCursor(mx);

                handlerToWait.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingMore = false;
                    }
                }, 2000);

                break;
            default:
                throw new IllegalArgumentException("no loader id handled!");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.i_search:
                Toast.makeText(getActivity(), "search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.i_view:
                Toast.makeText(getActivity(), "view", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
