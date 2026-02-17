package com.Varrell.gamemodeAPI.Component.Data;

import com.Varrell.gamemodeAPI.GamemodeAPI;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class PlayerPOVComponent implements Component<EntityStore> {
    private String POVName;

    public PlayerPOVComponent(String POVName) {
        this.POVName = POVName;
    }

    public String getPOVName() {
        return POVName;
    }

    public void setPOVName(String POVName) {
        this.POVName = POVName;
    }

    public static @NonNull ComponentType<EntityStore, PlayerPOVComponent> getComponentType() {
        return GamemodeAPI.get().getPlayerPOVComponentType();
    }

    @Override
    public @Nullable Component<EntityStore> clone() {
        return new PlayerPOVComponent(POVName);
    }
}
