package com.Varrell.gamemodeAPI.Camera.MouseControl;

import com.hypixel.hytale.math.iterator.BlockIterator;
import com.hypixel.hytale.math.util.ChunkUtil;
import com.hypixel.hytale.server.core.asset.type.blocktype.config.BlockType;
import com.hypixel.hytale.server.core.event.events.player.PlayerMouseButtonEvent;
import org.jspecify.annotations.NonNull;

public class DefaultMouseControl extends AbstractMouseControl {

    /* Click selection and data sorting happen in the AbstractMouseControl class.
    ** If needed, uncomment the 'onPlayerMouseButton' methode to customize it
    */

    //@Override
    //public void onPlayerMouseButton(@Nonnull PlayerMouseButtonEvent event) {}

    @Override
    protected void onLeftClick(@NonNull PlayerMouseButtonEvent event) {
        if (event.getItemInHand() != null && event.getItemInHand().hasBlockType() && targetBlock != null) {
            String key = event.getItemInHand().getId();
            int blockId = BlockType.getAssetMap().getIndex(key);
            if (blockId == Integer.MIN_VALUE) {
                throw new IllegalArgumentException("Unknown key! " + key);
            }

            if (!lastTargetBlock.equals(targetBlock)) {
                BlockIterator.iterateFromTo((double)lastTargetBlock.getX(), (double)(lastTargetBlock.getY() + 1), (double)lastTargetBlock.getZ(), (double)targetBlock.getX(), (double)(targetBlock.getY() + 1), (double)targetBlock.getZ(), (xx, y, zx, px, py, pz, qx, qy, qz) -> {
                    world.getChunk(ChunkUtil.indexChunkFromBlock(xx, zx)).setBlock(xx, y, zx, blockId);
                    return true;
                });
            } else {
                int x = targetBlock.getX();
                int z = targetBlock.getZ();
                world.getChunk(ChunkUtil.indexChunkFromBlock(x, z)).setBlock(x, targetBlock.getY() + 1, z, blockId);
            }
        }
    }

    @Override
    protected void onRightClick(@NonNull PlayerMouseButtonEvent event) {
        if (!lastTargetBlock.equals(targetBlock)) {
            BlockIterator.iterateFromTo(lastTargetBlock, targetBlock, (xx, y, zx, px, py, pz, qx, qy, qz) -> {
                world.getChunk(ChunkUtil.indexChunkFromBlock(xx, zx)).setBlock(xx, y, zx, 0);
                return true;
            });
        } else {
            int x = targetBlock.getX();
            int z = targetBlock.getZ();
            world.getChunk(ChunkUtil.indexChunkFromBlock(x, z)).setBlock(x, targetBlock.getY(), z, 0);
        }
    }

    @Override
    protected void onMiddleClick(@NonNull PlayerMouseButtonEvent event) {
        if (event.getItemInHand() != null && event.getItemInHand().hasBlockType() && targetBlock != null) {
            String key = event.getItemInHand().getId();
            int blockId = BlockType.getAssetMap().getIndex(key);
            if (blockId == Integer.MIN_VALUE) {
                throw new IllegalArgumentException("Unknown key! " + key);
            }

            if (!lastTargetBlock.equals(targetBlock)) {
                BlockIterator.iterateFromTo(lastTargetBlock, targetBlock, (xx, y, zx, px, py, pz, qx, qy, qz) -> {
                    world.getChunk(ChunkUtil.indexChunkFromBlock(xx, zx)).setBlock(xx, y, zx, blockId);
                    return true;
                });
            } else {
                int x = targetBlock.getX();
                int z = targetBlock.getZ();
                world.getChunk(ChunkUtil.indexChunkFromBlock(x, z)).setBlock(x, targetBlock.getY(), z, blockId);
            }
        }
    }

    @Override
    protected void onThumb1Click(@NonNull PlayerMouseButtonEvent event) {

    }

    @Override
    protected void onThumb2Click(@NonNull PlayerMouseButtonEvent event) {

    }
}
