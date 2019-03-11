package org.com.asapplication.mvvm.model;

import com.google.gson.Gson;

/**
 * Class Des:
 * Created by bjh on 2019/3/8.
 */
public class WeatherModel {
    private String weatherInfo ="{\"city\":\"杭州\",\"cityid\":\"101210101\",\"temp1\":\"5℃\",\"temp2\":\"20℃\",\"weather\":\"晴转多云\",\"img1\":\"n0.gif\",\"img2\":\"d1.gif\",\"ptime\":\"18:00\"}";

    public WeatherInfo getWeatherInfoNet() {
        return new Gson().fromJson(weatherInfo,WeatherInfo.class);
    }
}
