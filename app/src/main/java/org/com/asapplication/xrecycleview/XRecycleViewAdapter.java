package org.com.asapplication.xrecycleview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utils.library.base.BaseRecyclerAdapter;

import org.com.asapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Class Des:
 * Created by bjh on 2019/3/11.
 */
public class XRecycleViewAdapter extends BaseRecyclerAdapter<String,XRecycleViewAdapter.RecycleViewHolder> {

    private Context context;

    public XRecycleViewAdapter(Context context) {
        super(context);
        this.context = context;

    }

    @Override
    public void onBindViewHolder(RecycleViewHolder viewHolder, String s, int position) {
        viewHolder.tvTitle.setText(s);
    }


    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_xrecycleview, parent, false);
        return new RecycleViewHolder(inflate);
    }
    static  class RecycleViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.iv)
        ImageView iv;
        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
