package com.feildmaster.event;

import java.util.Collection;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;

public final class EmoteEvent extends MessageEvent {
    private static final HandlerList handlers = new HandlerList();

    private final CommandSender sender;

    public EmoteEvent(final CommandSender sender, final String message) {
        super(message);
        this.sender = sender;
    }

    public EmoteEvent(final CommandSender sender, final String message, CommandSender... recipients) {
        super(message, recipients);
        this.sender = sender;
    }

    public EmoteEvent(final CommandSender sender, final String message, Collection<CommandSender> recipients) {
        super(message, recipients);
        this.sender = sender;
    }

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
