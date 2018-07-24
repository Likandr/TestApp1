package com.likandr.condortestapp.ui.main.some_data.view;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.likandr.condortestapp.R;
import com.likandr.condortestapp.data._models.SomeData;

import java.util.ArrayList;
import java.util.List;

public class SomeDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int REGULAR_ITEM = 1;
    private final int FOOTER_ITEM = 0;

    private List<SomeData> mDataset = new ArrayList<>();

    private final LayoutInflater inflater;

    private Handler mHandler;
    @Nullable private OnClickListener mOnClickListener;

    public SomeDataAdapter(FragmentActivity context) {
        mHandler = context.getWindow().getDecorView().getHandler();
        if (mHandler == null) mHandler = new Handler();
        inflater = LayoutInflater.from(context);
    }

    public void setOnClickListener(@Nullable OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public static class SomeDataHolder extends RecyclerView.ViewHolder {
        SomeDataHolder(SomeDataItem someDataItem) {
            super(someDataItem);
        }

        private void bindTo(SomeData someData) {
            ((SomeDataItem) itemView).bindTo(someData);
        }
    }
    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
            progressBar = v.findViewById(R.id.progressBar);
        }
    }

    @Override public int getItemViewType(int position) {
        return mDataset.get(position) != null ? REGULAR_ITEM : FOOTER_ITEM;
    }

    @NonNull @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case REGULAR_ITEM: {
                return new SomeDataHolder((SomeDataItem) inflater.inflate(
                        R.layout.some_data_item, parent, false));
            }
            case FOOTER_ITEM: {
                return new ProgressViewHolder(inflater.inflate(
                        R.layout.somedata_loadmore_item, parent, false));
            }
            default:
                throw new RuntimeException("The viewType has to be REGULAR_ITEM or FOOTER_ITEM");
        }
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SomeDataHolder){
            ((SomeDataHolder)holder).bindTo(mDataset.get(position));
            ((SomeDataHolder)holder).itemView.setOnClickListener(v -> {
                if (mOnClickListener != null) {
                    mOnClickListener.onItemClicked(mDataset.get(position));
                }
            });
        } else {
            ((ProgressViewHolder)holder).progressBar.setIndeterminate(true);
        }
    }

    @Override public int getItemCount() {
        return mDataset.size();
    }

    public void setItems(List<SomeData> dataList) {
        this.mDataset = dataList;
        notifyDataSetChanged();
    }

    public void addItems(List<SomeData> items) {
        removeProgress();
        mDataset.addAll(items);
        mHandler.post(() -> notifyItemInserted(getItemCount()));
    }

    public void addProgress() {
        mDataset.add(null);
        mHandler.post(() -> notifyItemInserted(getItemCount()));
    }

    public void removeProgress() {
        mDataset.remove(getItemCount() - 1);
        mHandler.post(() -> notifyItemRemoved(getItemCount()));
    }

    public interface OnClickListener {
        void onItemClicked(SomeData someData);
    }
}
