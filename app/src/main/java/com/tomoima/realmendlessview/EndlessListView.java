package com.tomoima.realmendlessview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.tomoima.realmendlessview.adapters.SimpleArrayAdapter;
import com.tomoima.realmendlessview.models.SimpleData;

import java.util.List;

/**
 * Created by tomoaki on 2015/05/31.
 */
public class EndlessListView extends ListView implements AbsListView.OnScrollListener{

    EndlessListener mListener;
    View mFooterView;
    SimpleArrayAdapter mAdapter;
    boolean mIsLoading = false;
    boolean mIsBottom = false;
    public EndlessListView(Context context) {
        super(context);
        this.setOnScrollListener(this);
    }

    public EndlessListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnScrollListener(this);
    }

    public EndlessListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnScrollListener(this);
    }

    public void setEndlessListener(EndlessListener l){
        mListener = l;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mIsBottom) return;
        if (getAdapter() == null) return;
        if ( getAdapter().getCount() == 0) return;

        int count = visibleItemCount + firstVisibleItem;
        if (count >= totalItemCount && !mIsLoading){

            mIsLoading = true;
            this.addFooterView(mFooterView);
            mListener.loadData();
        }

    }

    public void setLoadingView(int resId){
        LayoutInflater layoutInflater = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mFooterView = layoutInflater.inflate(resId,null);
    }

    public void setAdapter(SimpleArrayAdapter adapter){
        super.setAdapter(adapter);
        mAdapter = adapter;
        this.removeFooterView(mFooterView);

    }

    public void addNewData(List<SimpleData> data){
        mIsLoading = false;
        SimpleArrayAdapter adapter = (SimpleArrayAdapter)this.getAdapter();
        setAdapter(null);
        this.removeFooterView(mFooterView);
        setAdapter(adapter);
        if(data != null) {
            mAdapter.addAll(data);
            mAdapter.notifyDataSetChanged();
        }
        setSelection(getAdapter().getCount()-1);
    }

    public void setIsBottom(Boolean isBottom){
        mIsBottom = isBottom;
    }

    public interface EndlessListener{
        void loadData();
    }
}
