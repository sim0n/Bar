package dev.sim0n.base.event;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

@FunctionalInterface
public interface CustomEventHandler<T> extends EventExecutor {
    void callEvent(T event);

    default void execute(Listener listener, Event event) throws EventException {
        try {
            //noinspection unchecked
            callEvent((T) event);
        } catch (ClassCastException ignored) {}
    }
}
