package com.example.oneshotspear;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public class OneShotSpear implements ModInitializer {
    /** Toggled from the K menu. Works in singleplayer / LAN host (same JVM). */
    public static volatile boolean enabled = true;

    @Override
    public void onInitialize() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (enabled
                    && source.getEntity() instanceof Player player
                    && entity != player
                    && entity.level() instanceof ServerLevel level) {
                String id = BuiltInRegistries.ITEM.getKey(player.getMainHandItem().getItem()).getPath();
                if (id.endsWith("_spear")) {
                    entity.kill(level);
                    return false;
                }
            }
            return true;
        });
    }
}
