package com.farmersoplenty.registry;

import com.farmersoplenty.FarmersOPlenty;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * The mod's blocks. Empty for now — the Farmer's Delight cabinets arrive in step 6.
 *
 * Tip for step 6: register each cabinet's BlockItem into ModItems.ITEMS (not a separate
 * register) so cabinets flow into the creative tab / models / lang exactly like food items.
 */
public final class ModBlocks {
    private ModBlocks() {}

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FarmersOPlenty.MODID);

    // step 6: FIR_CABINET, PINE_CABINET, MAPLE_CABINET, ... (one per BOP wood type)

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
