package com.example.oneshotspear;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SpearMenuScreen extends Screen {
    private Button toggleButton;

    public SpearMenuScreen() {
        super(Component.literal("One Shot Spear"));
    }

    @Override
    protected void init() {
        int w = 200, h = 20;
        int x = this.width / 2 - w / 2;
        int y = this.height / 2 - 24;

        toggleButton = Button.builder(label(), b -> {
            OneShotSpear.enabled = !OneShotSpear.enabled;
            b.setMessage(label());
        }).bounds(x, y, w, h).build();
        this.addRenderableWidget(toggleButton);

        this.addRenderableWidget(Button.builder(Component.literal("Close"), b -> this.onClose())
                .bounds(x, y + 26, w, h).build());
    }

    private static Component label() {
        return Component.literal("One Shot Spear: " + (OneShotSpear.enabled ? "ON" : "OFF"));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
