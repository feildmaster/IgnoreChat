package com.feildmaster.event;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;

/**
 * An abstract event designed to be a common handler for generic "MessageEvents."
 * <p />
 * A message event has <b>recipients</b> and a <b>message</b>.
 * <p />
 * Implementations may override the method {@link #setMessage(java.lang.String)}
 * to make it a no-operation function.
 *
 * @author feildmaster
 */
public abstract class MessageEvent extends Event {
    private final Set<CommandSender> recipients = new HashSet<CommandSender>();
    private final String original;
    private String message;

    /**
     * Crates a MessageEvent with the provided message, and no recipients.
     *
     * @param message the original message
     */
    public MessageEvent(String message) {
        this.original = this.message = message;
    }

    /**
     * Crates a MessageEvent with the provided message and recipients.
     *
     * @param message the original message
     * @param recipients the recipients of the message
     */
    public MessageEvent(String message, CommandSender... recipients) {
        this(message);
        Collections.addAll(getRecipients(), recipients);
    }

    /**
     * Crates a MessageEvent with the provided message and recipients.
     *
     * @param message the original message
     * @param recipients the recipients of the message
     * @throws NullPointerException if recipients is null
     */
    public MessageEvent(String message, Collection<CommandSender> recipients) {
        this(message);
        getRecipients().addAll(recipients);
    }

    /**
     * Gets the original message to be sent.
     *
     * @return the original message
     */
    public final String getOriginalMessage() {
        return original;
    }

    /**
     * Gets the message to be sent.
     *
     * @return the message to be sent
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message to be sent.
     *
     * @param message new message to be sent
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets a set of {@link CommandSender's} the message will be sent to.
     * <p />
     * <b>This set is not guaranteed to be mutable and may throw an {@link UnsupportedOperationException} if an ImmutableSet is provided.</b>
     *
     * @return all command senders the message will be sent to
     */
    public final Set<CommandSender> getRecipients() {
        return recipients;
    }
}
