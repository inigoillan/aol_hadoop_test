package com.aol.logprocessor.parser.address;

import java.net.InetAddress;

/**
 * An {@link Address} for {@link InetAddress} types
 *
 * @author <a href="mailto:inigo.illan@gmail.com">Inigo Illan</a>
 * @since 1.0
 */
public class IPAddress implements Address<InetAddress> {

    InetAddress address;

    public IPAddress(InetAddress address) {
        this.address = address;
    }

    public InetAddress get() {
        return this.address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof IPAddress))
            return false;

        if (this == o)
            return true;

        IPAddress other = (IPAddress) o;

        return this.get().equals(other.get());
    }
}
