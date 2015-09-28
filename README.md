This application is a maven project. You should be able to import it into any IDE. We've tested it with IntelliJ 14.
The maven dependencies we use come straight from cloudera repositories.
You will not need an actual hadoop environment to run this application as MapReduce will run in local mode.
You do not need to assemble this project into a jar and running the application from your IDE should suffice.

By default this application does just reads 'input-data.log' file passes it's contents through a mapper and a reducer
and writes out the same file into the output folder.

We've also provided a tiny application GeoIpExample.java demonstrating how to use GeoIP library to detect a country from an IP address.
To run it you need to provide two arguments: a geo ip database file and an ip address. The geoip database file is is provided
inside the project: GeoLite2-City.mmdb

If you have any questions/problems please do get in touch with us.