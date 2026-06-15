package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import com.farmersoplenty.registry.ModItems;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * The mod's blocks. Cabinets reuse Farmer's Delight's own CabinetBlock class wholesale,
 * so all the container/menu/redstone/open-close behaviour comes for free. The only wiring
 * needed is attaching our blocks to FD's cabinet BlockEntityType - see FarmersDelightCompat.
 *
 * The cabinet(...) helper also registers the matching BlockItem into ModItems, so cabinets
 * flow into the creative tab / lang / item-model pipeline exactly like the food items.
 */
public final class ModBlocks {
    private ModBlocks() {}

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FarmersOPlenty.MODID);

    /** Every cabinet, for BE-type wiring, blockstate/loot/tag generation, and the creative tab. */
    public static final List<DeferredBlock<CabinetBlock>> CABINETS = new ArrayList<>();

    // ---- Cabinets (one per BOP wood type) ----
    public static final DeferredBlock<CabinetBlock> FIR_CABINET = cabinet("fir_cabinet");
    public static final DeferredBlock<CabinetBlock> WILLOW_CABINET = cabinet("willow_cabinet");

    /** Registers a cabinet block, records it, and registers its BlockItem (via ModItems). */
    private static DeferredBlock<CabinetBlock> cabinet(String name) {
        DeferredBlock<CabinetBlock> block = BLOCKS.registerBlock(
                name,
                CabinetBlock::new,
                BlockBehaviour.Properties.ofFullCopy(Blocks.BARREL));
        CABINETS.add(block);
        ModItems.blockItem(block);
        return block;
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}