package com.Varrell.gamemodeAPI.Camera.MouseControl;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.protocol.MouseButtonState;
import com.hypixel.hytale.protocol.MouseButtonType;
import com.hypixel.hytale.server.core.entity.entities.player.CameraManager;
import com.hypixel.hytale.server.core.event.events.player.PlayerMouseButtonEvent;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public abstract class AbstractMouseControl {
    protected Ref<EntityStore> ref;
    protected Store<EntityStore> store;
    protected World world;
    protected Vector3i targetBlock;
    protected CameraManager cameraManagerComponent;
    protected Vector3i lastTargetBlock;

    public void onPlayerMouseButton(@Nonnull PlayerMouseButtonEvent event) {
        if (event.getMouseButton().state == MouseButtonState.Released) {
            ref = event.getPlayerRef();
            if (ref.isValid()) {
                store = ref.getStore();
                world = ((EntityStore) store.getExternalData()).getWorld();
                targetBlock = event.getTargetBlock();
                cameraManagerComponent = (CameraManager) store.getComponent(ref, CameraManager.getComponentType());
                assert cameraManagerComponent != null;
                lastTargetBlock = cameraManagerComponent.getLastMouseButtonPressedPosition(event.getMouseButton().mouseButtonType);
                if (event.getMouseButton().mouseButtonType == MouseButtonType.Left)
                    onLeftClick(event);
                else if (event.getMouseButton().mouseButtonType == MouseButtonType.Right)
                    onRightClick(event);
                else if (event.getMouseButton().mouseButtonType == MouseButtonType.Middle)
                    onMiddleClick(event);
                else if (event.getMouseButton().mouseButtonType == MouseButtonType.X1)
                    onThumb1Click(event);
                else if (event.getMouseButton().mouseButtonType == MouseButtonType.X2)
                    onThumb2Click(event);
            }
        }
    }

    protected abstract void onLeftClick(@Nonnull PlayerMouseButtonEvent event);
    protected abstract void onRightClick(@Nonnull PlayerMouseButtonEvent event);
    protected abstract void onMiddleClick(@Nonnull PlayerMouseButtonEvent event);
    protected abstract void onThumb1Click(@Nonnull PlayerMouseButtonEvent event);
    protected abstract void onThumb2Click(@Nonnull PlayerMouseButtonEvent event);
}
