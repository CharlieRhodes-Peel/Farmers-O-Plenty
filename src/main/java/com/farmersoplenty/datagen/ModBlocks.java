package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import com.farmersoplenty.registry.CropBlockBase;
import com.farmersoplenty.registry.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.block.CabinetBlock;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

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
    public static final DeferredBlock<CabinetBlock> DEAD_CABINET = cabinet("dead_cabinet");
    public static final DeferredBlock<CabinetBlock> UMBRAN_CABINET = cabinet("umbran_cabinet");
    public static final DeferredBlock<CabinetBlock> REDWOOD_CABINET = cabinet("redwood_cabinet");
    public static final DeferredBlock<CabinetBlock> PINE_CABINET = cabinet("pine_cabinet");
    public static final DeferredBlock<CabinetBlock> PALM_CABINET = cabinet("palm_cabinet");
    public static final DeferredBlock<CabinetBlock> MAPLE_CABINET = cabinet("maple_cabinet");
    public static final DeferredBlock<CabinetBlock> MAHOGANY_CABINET = cabinet("mahogany_cabinet");
    public static final DeferredBlock<CabinetBlock> MAGIC_CABINET = cabinet("magic_cabinet");
    public static final DeferredBlock<CabinetBlock> JACARANDA_CABINET = cabinet("jacaranda_cabinet");
    public static final DeferredBlock<CabinetBlock> HELLBARK_CABINET = cabinet("hellbark_cabinet");
    public static final DeferredBlock<CabinetBlock> EMPYREAL_CABINET = cabinet("empyreal_cabinet");

    // Crops
    public static final List<DeferredBlock<? extends CropBlock>> CROPS = new ArrayList<>();

    public static final DeferredBlock<CropBlockBase> BARLEY_CROP = registerCrop("barley", () -> ModBlocks.BARLEY_SEEDS.get());
    public static final DeferredItem<Item> BARLEY_SEEDS = ModItems.seedItem("barley_seeds", BARLEY_CROP);

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

    private static DeferredBlock<CropBlockBase> registerCrop(String name, Supplier<ItemLike> seed) {
        DeferredBlock<CropBlockBase> block = BLOCKS.registerBlock(name,
                props -> new CropBlockBase(props, seed),
                BlockBehaviour.Properties.ofFullCopy(Blocks.WHEAT));
        CROPS.add(block);
        return block;
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}