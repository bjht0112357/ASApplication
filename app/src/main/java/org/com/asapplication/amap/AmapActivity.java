package org.com.asapplication.amap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.animation.Animation;
import com.amap.api.maps.model.animation.TranslateAnimation;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.utils.library.utils.AppUtils;
import com.utils.library.utils.ToastUtils;

import org.com.asapplication.R;
import org.com.asapplication.apputils.AppLogger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AmapActivity extends AppCompatActivity {


    @BindView(R.id.map)
    MapView map;
    private double mLocationLatitude;
    private double mLocationLongitude;
    private AMap aMap;
    private static final  int MY_ACCESS_FINE_LOCATION=2;
    private InfoWinAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amap);
        setTitle("高德地图");
        ButterKnife.bind(this);
        map.onCreate(savedInstanceState);// 此方法必须重写
        aMap = map.getMap();
        UiSettings uiSettings =  aMap.getUiSettings();
        //隐藏高德地图入图标
        uiSettings.setLogoBottomMargin(-50);
        //定位权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_ACCESS_FINE_LOCATION);
            }
        }else {
            startLocaion();
        }

//        getScreenCenterLatLng();
        getDistance();
    }

    /**
     * 获取两个经纬点的距离
     * @return distance
     */
    private float getDistance() {
        LatLng latlngA = new LatLng(24.4928533,118.177800);
        LatLng latlngB = new LatLng(24.8928533,118.877800);
        float distance = AMapUtils.calculateLineDistance(latlngA, latlngB);
        AppLogger.e("distance="+distance+"米");
        return distance;
    }

    /**
     * 获取屏幕中心点的经纬度
     */
    private void getScreenCenterLatLng() {
        aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                LatLng target = cameraPosition.target;
                AppLogger.e("target latitude" +target.latitude+"target longitude" +target.longitude);
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_ACCESS_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){//用户同意权限,执行我们的操作
                    startLocaion();//开始定位
                }else{//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(AmapActivity.this,"未开启定位权限,请手动到设置去开启权限",Toast.LENGTH_LONG).show();
                }
                break;
            default:break;
        }
    }

    private void startLocaion() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        myLocationStyle.strokeColor(Color.GRAY);//设置定位蓝点精度圆圈的边框颜色的方法。
        myLocationStyle.radiusFillColor(Color.parseColor("#80C9D8F5"));//设置定位蓝点精度圆圈的填充颜色的方法。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        // MAP_TYPE_NAVI  导航地图
        // MAP_TYPE_NIGHT 夜景地图
        // MAP_TYPE_NORMAL 白昼地图（即普通地图）
        // MAP_TYPE_SATELLITE 卫星图
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.showMyLocation(true);
        aMap.setOnMyLocationChangeListener(location -> {
            if (location != null) {
                Bundle bundle = location.getExtras();
                if (bundle != null) {
                    mLocationLatitude = location.getLatitude();
                    mLocationLongitude = location.getLongitude();
                    AppLogger.e("mLocationLatitude ="+mLocationLatitude);
                    AppLogger.e("mLocationLongitude ="+mLocationLongitude);
                    if (mLocationLatitude > 0 && mLocationLongitude > 0) {
                        //高德定位后缩放级别的设置  v 20
                        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new LatLng(mLocationLatitude, mLocationLongitude),
                                18);
                        aMap.moveCamera(cu);
                    }
                }
//                addMarkerInScreenCenter();
                addMarker();
            }else {
                ToastUtils.show(AmapActivity.this,"定位失败，请检查您的定位权限");
            }
        });
    }
    private Marker marker;
    private void addMarker(){
        //地图触摸事件
        aMap.setOnMapTouchListener(motionEvent -> {
            if (aMap != null && marker != null) {
                if (marker.isInfoWindowShown()){
                    marker.hideInfoWindow();
                }
            }
        });
        adapter = new InfoWinAdapter();
        aMap.setInfoWindowAdapter(adapter);
        LatLng latLng = new LatLng(24.4928533,118.177800);
        marker = aMap.addMarker(new MarkerOptions()
                             .position(latLng).title("软件园").snippet("望海路十九号")
                             .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka)));
        markerJump(aMap, marker);
//        getPOI(marker);
    }
    //始终固定在屏幕中心位置的点
    private void addMarkerInScreenCenter() {
        if (marker == null) {
            marker = aMap.addMarker(new MarkerOptions().zIndex(2)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_marka)));
        }
        marker.setAnchor(0.5f, 1.0f);
        LatLng latLng = aMap.getCameraPosition().target;
        Point screenPosition = aMap.getProjection().toScreenLocation(latLng);
        marker.setPositionByPixels(screenPosition.x, screenPosition.y);
        marker.setClickable(false);
        markerJump(aMap, marker);
    }

    /**
     * 获取附近的公司
     * @param marker marker
     */
    private void getPOI(Marker marker){
        PoiSearch.Query query = new PoiSearch.Query("", "公司", "");
        query.setPageSize(1000);
        PoiSearch search = new PoiSearch(this, query);
        search.setBound(new PoiSearch.SearchBound(new LatLonPoint(marker.getPosition().latitude, marker.getPosition().longitude), 1000));
        search.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult result, int i) {
                ArrayList<PoiItem> pois = result.getPois();
                for (PoiItem poi : pois) {
                    String company = poi.getTitle();
                    AppLogger.e(company);
                }

            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        search.searchPOIAsyn();

    }

    /**
     * 给中心点添加跳跃动画
     * @param aMap aMap
     * @param marker marker
     */
    public void markerJump(AMap aMap, Marker marker) {
        if (marker != null) {
            final LatLng latLng = marker.getPosition();
            Point point = aMap.getProjection().toScreenLocation(latLng);
            point.y -= AppUtils.dip2px(this, 20);
            LatLng target = aMap.getProjection()
                    .fromScreenLocation(point);
            //使用TranslateAnimation,填写一个需要移动的目标点
            Animation animation = new TranslateAnimation(target);
            animation.setInterpolator(input -> {
                // 模拟重加速度的interpolator
                if (input <= 0.5) {
                    return (float) (0.5f - 2 * (0.5 - input) * (0.5 - input));
                } else {
                    return (float) (0.5f - Math.sqrt((input - 0.5f) * (1.5f - input)));
                }
            });
            //整个移动所需要的时间
            animation.setDuration(1000);
            //设置动画
            marker.setAnimation(animation);
            //开始动画
            marker.startAnimation();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        map.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        map.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        map.onSaveInstanceState(outState);
    }

}
