package com.feildmaster.event;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;

public abstract class MessageEvent extends Event {
    private final Set<CommandSender> recipients = new HashSet<CommandSender>();
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(String message, CommandSender... recipients) {
        this(message);
        Collections.addAll(getRecipients(), recipients);
    }

    public MessageEvent(String message, Collection<CommandSender> recipients) {
        this(message);
        getRecipients().addAll(recipients);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public final Set<CommandSender> getRecipients() {
        return recipients;
    }
}
