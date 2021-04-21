package dev.sim0n.base.event;

import dev.sim0n.bar.Bar;
import lombok.experimental.UtilityClass;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredListener;

import java.lang.reflect.Method;

@UtilityClass
public class EventHandler {
    private final Bar plugin = Bar.getInstance();

    public <T extends Event> void register(Class<T> clazz, CustomEventHandler<T> handler, EventPriority priority, boolean ignoreCancelled) {
        try {
            HandlerList handlerList = getHandlerList(clazz);

            handlerList.register(new RegisteredListener(plugin.getEventListener(), handler, priority,
                    plugin, ignoreCancelled));
        } catch (Exception e) {
        }
    }

    public <T extends Event> void register(Class<T> clazz, CustomEventHandler<T> handler, EventPriority priority) {
        try {
            HandlerList handlerList = getHandlerList(clazz);

            handlerList.register(new RegisteredListener(plugin.getEventListener(), handler, priority,
                    plugin, false));
        } catch (Exception e) {
        }
    }

    public <T extends Event> void register(Class<T> clazz, CustomEventHandler<T> handler) {
        try {
            HandlerList handlerList = getHandlerList(clazz);

            handlerList.register(new RegisteredListener(plugin.getEventListener(), handler, EventPriority.NORMAL,
                    plugin, false));
        } catch (Exception e) {
        }
    }

    private <T> HandlerList getHandlerList(Class<T> clazz) throws Exception {
        Method method = clazz.getDeclaredMethod("getHandlerList");
        method.setAccessible(true);

        return (HandlerList) method.invoke(null);
    }
}
