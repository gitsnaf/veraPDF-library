/**
 * 
 */
package org.verapdf.pdfa.results;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author <a href="mailto:carl@openpreservation.org">Carl Wilson</a>
 *
 */
@XmlRootElement(name = "location")
final class LocationImpl implements Location {
    private static final LocationImpl DEFAULT = new LocationImpl();
    private static final String DEREF_REGEX = "\\([0-9]{1,} ([a-zA-Z]{4})";
    private static final String DEREF_REPL = "\\(";
    private static final Pattern DEREF_PATTERN = Pattern.compile(DEREF_REGEX);
    @XmlElement
    private final String level;
    @XmlElement
    private final String context;

    private LocationImpl() {
        this("level", "context");
    }

    /**
     * @param level
     */
    private LocationImpl(final String level, final String context) {
        super();
        this.level = level;
        this.context = context;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public String getLevel() {
        return this.level;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public String getContext() {
        return this.context;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((this.context == null) ? 0 : this.context.hashCode());
        result = prime * result
                + ((this.level == null) ? 0 : this.level.hashCode());
        return result;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Location))
            return false;
        Location other = (Location) obj;
        if (this.context == null) {
            if (other.getContext() != null)
                return false;
        } else if (!this.context.equals(other.getContext()))
            return false;
        if (this.level == null) {
            if (other.getLevel() != null)
                return false;
        } else if (!this.level.equals(other.getLevel()))
            return false;
        return true;
    }

    /**
     * { @inheritDoc }
     */
    @Override
    public final String toString() {
        return "Location [level=" + this.level + ", context=" + this.context
                + "]";
    }

    static LocationImpl defaultInstance() {
        return DEFAULT;
    }

    static LocationImpl fromValues(final String level, final String context) {
        String deRefdContext = context;
        Matcher matcher = DEREF_PATTERN.matcher(context);
        if (matcher.find())
            deRefdContext = matcher.replaceAll(DEREF_REPL + matcher.group(1));
        return new LocationImpl(level, deRefdContext);
    }

    static class Adapter extends XmlAdapter<LocationImpl, Location> {
        @Override
        public Location unmarshal(LocationImpl locationImpl) {
            return locationImpl;
        }

        @Override
        public LocationImpl marshal(Location location) {
            return (LocationImpl) location;
        }
    }

}
