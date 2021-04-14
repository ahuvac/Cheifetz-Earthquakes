package cheifetz.geojson;

import java.util.List;

public class GeoJsonFeed {
    List<Feature> features;
    FeatureProperties properties;
}

class Feature {
    FeatureProperties properties;
    Geometry geometry;
}

class FeatureProperties {
    double mag;
    String place;
    long time;
}

class Geometry{
    List<Double>coordinates;
}
