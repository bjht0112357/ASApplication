package org.com.asapplication.mvvm.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import org.com.asapplication.mvvm.model.WeatherInfo;
import org.com.asapplication.mvvm.model.WeatherModel;

/**
 * Class Des:
 * Created by bjh on 2019/3/8.
 */
public class QueryWeatherViewModel {
    private static final String TAG = "QueryWeatherViewModel";

    public final ObservableBoolean loading = new ObservableBoolean(false);

    public final ObservableBoolean loadingSuccess = new ObservableBoolean(false);

    public final ObservableBoolean loadingFailure = new ObservableBoolean(false);

    public final ObservableField<String> city = new ObservableField<>();

    public final ObservableField<String> cityId = new ObservableField<>();

    public final ObservableField<String> temp1 = new ObservableField<>();

    public final ObservableField<String> temp2 = new ObservableField<>();

    public final ObservableField<String> weather = new ObservableField<>();

    public final ObservableField<String> time = new ObservableField<>();
    private final WeatherModel weatherModel;


    public QueryWeatherViewModel() {
        weatherModel = new WeatherModel();
    }

    public void queryWeather() {
        loading.set(true);
        loadingSuccess.set(false);
        loadingFailure.set(false);
        WeatherInfo weatherInfo= weatherModel.getWeatherInfoNet();
        city.set(weatherInfo.getCity());
        cityId.set(weatherInfo.getCityid());
        temp1.set(weatherInfo.getTemp1());
        temp2.set(weatherInfo.getTemp2());
        weather.set(weatherInfo.getWeather());
        time.set(weatherInfo.getPtime());
        loading.set(false);
        loadingSuccess.set(true);
    }
}
