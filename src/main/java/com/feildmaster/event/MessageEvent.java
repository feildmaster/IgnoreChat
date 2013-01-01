package com.feildmaster.event;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.Validate;
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
    private final Set<CommandSender> recipients;
    private final String original;
    private String message;

    /**
     * Crates a MessageEvent with the provided message, and no recipients.
     *
     * @param message the original message
     */
    public MessageEvent(final String message) {
        this.original = this.message = message;
        this.recipients = new HashSet<CommandSender>();
    }

    /**
     * Crates a MessageEvent with the provided message and recipients.
     *
     * @param message the original message
     * @param recipients the recipients of the message
     */
    public MessageEvent(final String message, final CommandSender... recipients) {
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
    public MessageEvent(final String message, final Collection<CommandSender> recipients) {
        this(message);
        getRecipients().addAll(recipients);
    }

    /**
     * Creates a new MessageEvent with the provided message and recipients.
     * <p />
     * The {@link Set} provided will be used as the internal set. This allows
     * you to use an immutable set, if you so wish.
     *
     * @param message the original message
     * @param recipients the recipients of the message
     * @throws IllegalArgumentException if recipients is null
     */
    public MessageEvent(final String message, final Set<CommandSender> recipients) {
        Validate.notNull(recipients, "Recipients may not be null");
        this.original = this.message = message;
        this.recipients = recipients;
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
