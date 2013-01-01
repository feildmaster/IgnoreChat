package com.feildmaster.event;

import java.util.Collection;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

/**
 * An event called on the "Emote" command (/me).<br />
 * The event stores a <b>sender</b>, a <b>message</b>, and <b>recipients</b>.
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
     */
    public EmoteEvent(final CommandSender sender, final String message) {
        super(message);
        this.sender = sender;
    }

    /**
     * Crates a new event with the provided sender, message, and recipients.
     *
     * @param sender the sender of the Emote
     * @param message the original (full) message
     * @param recipients the recipients of the message
     */
    public EmoteEvent(final CommandSender sender, final String message, CommandSender... recipients) {
        super(message, recipients);
        this.sender = sender;
    }

    /**
     * Crates a new event with the provided sender, message, and recipients.
     *
     * @param sender the sender of the Emote
     * @param message the original (full) message
     * @param recipients the recipients of the message
     */
    public EmoteEvent(final CommandSender sender, final String message, Collection<CommandSender> recipients) {
        super(message, recipients);
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
