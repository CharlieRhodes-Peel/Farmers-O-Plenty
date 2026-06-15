package com.farmersoplenty.datagen.compat;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.datagen.ModBlocks;

import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

/**
 * FD's CabinetBlock.newBlockEntity() hardcodes FD's own CABINET BlockEntityType, whose valid-blocks
 * set only contains FD's cabinets. Our cabinets are CabinetBlocks too, so we just register them onto
 * that same type here. Without this, our cabinets would create a block entity the type rejects, and
 * their inventories would vanish on reload.
 *
 * BlockEntityTypeAddBlocksEvent is a mod-bus event, so this is registered on the mod event bus.
 */
@EventBusSubscriber(modid = FarmersOPlenty.MODID)
public final class FarmersDelightCompat {
    private FarmersDelightCompat() {}

    @SubscribeEvent
    static void addCabinetsToBlockEntity(BlockEntityTypeAddBlocksEvent event) {
        Block[] cabinets = ModBlocks.CABINETS.stream()
                .map(holder -> (Block) holder.get())
                .toArray(Block[]::new);
        event.modify(ModBlockEntityTypes.CABINET.get(), cabinets);
    }
}
