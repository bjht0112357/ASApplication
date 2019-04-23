package org.com.asapplication.amap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

import org.com.asapplication.MyApplication;
import org.com.asapplication.R;

/**
 * Class Des:
 * Created by bjh on 2019/3/13.
 */
public class InfoWinAdapter implements AMap.InfoWindowAdapter, View.OnClickListener {
    private Context mContext = MyApplication.getInstance().getBaseContext();
    private LatLng latLng;
    private LinearLayout call;
    private LinearLayout navigation;
    private TextView nameTV;
    private String agentName;
    private TextView addrTV;
    private String snippet;

    @Override
    public View getInfoWindow(Marker marker) {
        initData(marker);
        View view = initView();
        return view;
    }
    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    private void initData(Marker marker) {
        latLng = marker.getPosition();
        snippet = marker.getSnippet();
        agentName = marker.getTitle();
    }

    @NonNull
    private View initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_infowindow, null);
        navigation = view.findViewById(R.id.navigation_LL);
        call = view.findViewById(R.id.call_LL);
        nameTV = view.findViewById(R.id.agent_name);
        addrTV = view.findViewById(R.id.agent_addr);

        nameTV.setText(agentName);
        addrTV.setText("地址："+snippet);

        navigation.setOnClickListener(this);
        call.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.navigation_LL:  //点击导航

                break;

            case R.id.call_LL:  //点击打电话
                break;
        }
    }

}

