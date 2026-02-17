package com.Varrell.gamemodeAPI.Camera;

import com.hypixel.hytale.protocol.*;

import java.util.Dictionary;
import java.util.Hashtable;

public class CameraTemplates {
    private static Dictionary<String, ServerCameraSettings> cameraDict = new Hashtable<>();

    public static void init() {
        topDownCamera("topDown");
        sideViewCamera("sideView");
        isometricViewCamera("isometric");
    }

    public static ServerCameraSettings get(String key) {
        return cameraDict.get(key);
    }

    public static void set (String key, ServerCameraSettings value) {
        cameraDict.put(key, value);
    }

    private static void topDownCamera(String name) {
        if (cameraDict.get(name) != null) {
            return;
        }
        ServerCameraSettings cameraSettings = new ServerCameraSettings();
        cameraSettings.positionLerpSpeed = 0.2F;
        cameraSettings.rotationLerpSpeed = 0.2F;
        cameraSettings.distance = 20.0F;
        cameraSettings.displayCursor = true;
        cameraSettings.sendMouseMotion = true;
        cameraSettings.isFirstPerson = false;
        cameraSettings.movementForceRotationType = MovementForceRotationType.Custom;
        cameraSettings.eyeOffset = true;
        cameraSettings.positionDistanceOffsetType = PositionDistanceOffsetType.DistanceOffset;
        cameraSettings.rotationType = RotationType.Custom;
        cameraSettings.rotation = new Direction(0.0F, (-(float)Math.PI / 2F), 0.0F);
        cameraSettings.mouseInputType = MouseInputType.LookAtPlane;
        cameraSettings.planeNormal = new Vector3f(0.0F, 1.0F, 0.0F);
        set(name, cameraSettings);
    }

    private static void sideViewCamera(String name) {
        if (cameraDict.get(name) != null) {
            return;
        }
        ServerCameraSettings cameraSettings = new ServerCameraSettings();
        cameraSettings.positionLerpSpeed = 0.2F;
        cameraSettings.rotationLerpSpeed = 0.2F;
        cameraSettings.distance = 15.0F;
        cameraSettings.displayCursor = true;
        cameraSettings.isFirstPerson = false;
        cameraSettings.movementForceRotationType = MovementForceRotationType.Custom;
        cameraSettings.movementMultiplier = new Vector3f(1.0F, 1.0F, 0.0F);
        cameraSettings.eyeOffset = true;
        cameraSettings.positionDistanceOffsetType = PositionDistanceOffsetType.DistanceOffsetRaycast;
        cameraSettings.rotationType = RotationType.Custom;
        cameraSettings.mouseInputType = MouseInputType.LookAtPlane;
        cameraSettings.planeNormal = new Vector3f(0.0F, 0.0F, 1.0F);
        set(name, cameraSettings);
    }

    private static void isometricViewCamera(String name) {
        if (cameraDict.get(name) != null) {
            return;
        }
        ServerCameraSettings cameraSettings = new ServerCameraSettings();
        cameraSettings.positionLerpSpeed = 0.2f;
        cameraSettings.rotationLerpSpeed = 0.2f;
        cameraSettings.isFirstPerson = false;
        cameraSettings.movementForceRotationType = MovementForceRotationType.Custom;
        cameraSettings.distance = 6f;
        cameraSettings.allowPitchControls = false;
        cameraSettings.displayCursor = true;
        cameraSettings.applyLookType = ApplyLookType.Rotation;
        cameraSettings.rotationType = RotationType.Custom;
        Direction direction = new Direction(
                (float) Math.toRadians(45f),  // yaw
                (float) Math.toRadians(-35f), // pitch
                0f                            // roll
        );
        cameraSettings.rotation = direction;
        cameraSettings.movementForceRotation = direction;
        set(name, cameraSettings);
    }
}
