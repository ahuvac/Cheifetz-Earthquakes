package cheifetz.geojson;

import com.google.gson.Gson;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class DownloadGeoJson {
    public static void main(String[] args) throws IOException {
//        URL url = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
//        URLConnection connection = url.openConnection();
//        InputStream inputStream = connection.getInputStream();
//        Gson gson = new Gson();
//        InputStreamReader reader = new InputStreamReader(inputStream);
//        GeoJsonFeed feed = gson.fromJson(reader, GeoJsonFeed.class);
//        System.out.println(feed.features.size());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov")
                .addConverterFactory((GsonConverterFactory.create()))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        ///Dont use blocking get!!
        GeoJsonService service = retrofit.create(GeoJsonService.class);
        GeoJsonFeed feed = service.getSignificantEarthquakes()
                .blockingGet();

        Feature largest = feed.features.get(0);
        for (Feature feature : feed.features) {
            if(feature.properties.mag > largest.properties.mag){
                largest = feature;
            }

        }
        System.out.printf("%s %f %d",
                largest.properties.place,
                largest.properties.mag,
                largest.properties.time);

        System.exit(0);


    }
}
