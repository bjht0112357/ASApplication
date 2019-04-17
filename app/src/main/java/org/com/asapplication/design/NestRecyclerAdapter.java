package org.com.asapplication.design;

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
 * Created by bjh on 2019/4/3.
 */
public class NestRecyclerAdapter extends BaseRecyclerAdapter<String,NestRecyclerAdapter.NestViewHoder> {
    private Context context;
    public NestRecyclerAdapter(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public void onBindViewHolder(NestRecyclerAdapter.NestViewHoder viewHolder, String value, int position) {
        viewHolder.tvTitle.setText(value);
    }

    @NonNull
    @Override
    public NestRecyclerAdapter.NestViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xrecycleview, viewGroup,false);
        return new NestViewHoder(view);
    }
    static class NestViewHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.iv)
        ImageView iv;
        public NestViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
