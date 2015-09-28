package com.aol.logprocessor;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;

import java.io.File;
import java.net.InetAddress;

public class GeoIpExample {
    public static void main(String[] args) throws Exception {

        if (args.length < 2) {
            System.out.println("Usage: program geoCityDbPath ip");
            System.exit(1);
        }

        String geoCityDbPath = args[0];
        String ip = args[1];

        DatabaseReader.Builder builder = new DatabaseReader.Builder(new File(geoCityDbPath));
        DatabaseReader dbReader = builder.build();

        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse city = dbReader.city(ipAddress);

        System.out.println("Country for ip: " + ip + " is: " + city.getCountry());
    }
}
