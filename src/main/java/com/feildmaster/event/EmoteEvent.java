package com.feildmaster.event;

import java.util.Collection;
import java.util.Set;
import org.apache.commons.lang.Validate;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

/**
 * An event called on the "Emote" command (/me).<br />
 * The event stores a <b>sender</b>, a <b>message</b> and <b>recipients</b>.
 *
 * @author feildmaster
 */
public final class EmoteEvent extends MessageEvent {
    private static final HandlerList handlers = new HandlerList();

    private final CommandSender sender;

    /**
     * Creates a new EmoteEvent with the provided sender and message.
     * <p />
     * The event created will have no recipients.
     *
     * @param sender the sender of the Emote
     * @param message the original (full) message
     * @throws IllegalArgumentException if sender is null
     */
    public EmoteEvent(final CommandSender sender, final String message) {
        super(message);
        Validate.notNull(sender, "Sender may not be null");
        this.sender = sender;
    }

    /**
     * Crates a new event with the provided sender, message, and recipients.
     *
     * @param sender the sender of the Emote
     * @param message the original (full) message
     * @param recipients the recipients of the message
     * @throws IllegalArgumentException if sender is null
     */
    public EmoteEvent(final CommandSender sender, final String message, final CommandSender... recipients) {
        super(message, recipients);
        Validate.notNull(sender, "Sender may not be null");
        this.sender = sender;
    }

    /**
     * Crates a new event with the provided sender, message, and recipients.
     *
     * @param sender the sender of the Emote
     * @param message the original (full) message
     * @param recipients the recipients of the message
     * @throws IllegalArgumentException if sender is null
     */
    public EmoteEvent(final CommandSender sender, final String message, final Collection<CommandSender> recipients) {
        super(message, recipients);
        Validate.notNull(sender, "Sender may not be null");
        this.sender = sender;
    }

    /**
     * Crates a new event with the provided sender, message, and recipients.
     * <p />
     * The provided {@link Set} will be used as the returned recipients.
     *
     * @param sender the sender of the Emote
     * @param message the original (full) message
     * @param recipients the recipients of the message
     * @throws IllegalArgumentException if sender is null
     * @throws IlledalArgumentException if recipients is null
     */
    public EmoteEvent(final CommandSender sender, final String message, final Set<CommandSender> recipients) {
        super(message, recipients);
        Validate.notNull(sender, "Sender may not be null");
        this.sender = sender;
    }

    /**
     * Gets the sender of the emote.
     *
     * @return the sender
     */
    public CommandSender getSender() {
        return sender;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
