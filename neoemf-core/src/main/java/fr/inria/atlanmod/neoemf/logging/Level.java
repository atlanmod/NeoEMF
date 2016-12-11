package fr.inria.atlanmod.neoemf.logging;

/**
 * Levels used for identifying the severity of an event. Levels are organized from most specific to least:
 * <ul>
 * <li>{@link #ERROR} (most specific, little data)</li>
 * <li>{@link #WARN}</li>
 * <li>{@link #INFO}</li>
 * <li>{@link #DEBUG} (least specific, a lot of data)</li>
 * </ul>
 * <p/>
 * Typically, configuring a level in a filter or on a logger will cause logging events of that level and those that are
 * more specific to pass through the filter.
 */
public enum Level {
    DEBUG(org.apache.logging.log4j.Level.DEBUG),
    INFO(org.apache.logging.log4j.Level.INFO),
    WARN(org.apache.logging.log4j.Level.WARN),
    ERROR(org.apache.logging.log4j.Level.ERROR);

    private final org.apache.logging.log4j.Level level;

    Level(org.apache.logging.log4j.Level level) {
        this.level = level;
    }

    org.apache.logging.log4j.Level level() {
        return level;
    }
}
