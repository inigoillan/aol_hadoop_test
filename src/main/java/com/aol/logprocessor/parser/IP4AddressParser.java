package com.aol.logprocessor.parser;

import com.google.common.base.Throwables;
import org.apache.log4j.Logger;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class IP4AddressParser implements AddressParser<IPAddress> {
    private static final Logger LOG = Logger.getLogger(IP4AddressParser.class);

    public IPAddress parse(String address) {
        InetAddress inetAddress = null;
        try {
            inetAddress = Inet4Address.getByName(address);
        } catch (Exception e) {
            LOG.error(String.format("Couldn't parse: %s address", address));
            throw new ParsingException();
        }

        return new IPAddress(inetAddress);
    }

    public boolean canParse(String address) {
        return true;
    }
}
