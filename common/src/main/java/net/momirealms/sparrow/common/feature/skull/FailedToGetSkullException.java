package net.momirealms.sparrow.common.feature.skull;

/**
 * Exception thrown when the skull of a player could not be retrieved.
 */
public class FailedToGetSkullException extends RuntimeException {
    public FailedToGetSkullException(String playerName) {
        super("Failed to get skull of the given player " + playerName + "!");
    }

    public FailedToGetSkullException(String playerName, Throwable cause) {
        super("Failed to get skull of the given player " + playerName + "!", cause);
    }
}
