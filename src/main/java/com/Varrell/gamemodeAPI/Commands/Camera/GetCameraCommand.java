package com.Varrell.gamemodeAPI.Commands.Camera;

import com.Varrell.gamemodeAPI.Camera.CameraInitializer;
import com.Varrell.gamemodeAPI.Camera.CameraTemplates;
import com.Varrell.gamemodeAPI.Component.Data.PlayerPOVComponent;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.commands.player.camera.CameraDemo;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.player.CameraManager;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;


public class GetCameraCommand extends AbstractPlayerCommand {
    public GetCameraCommand() {
        super("get", "server.commands.worlds.desc");
    }

    @Override
    protected void execute(@NonNull CommandContext commandContext, @NonNull Store<EntityStore> store, @NonNull Ref<EntityStore> ref, @NonNull PlayerRef playerRef, @NonNull World world) {
        PlayerPOVComponent pPOV = store.getComponent(ref, PlayerPOVComponent.getComponentType());
        if (pPOV == null)
            commandContext.sendMessage(Message.raw("You do not have any custom POV applied"));
        else
            commandContext.sendMessage(Message.raw(pPOV.getPOVName()));
    }
}

