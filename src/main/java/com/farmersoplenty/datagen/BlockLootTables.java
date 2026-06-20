package com.farmersoplenty.datagen;

import com.farmersoplenty.datagen.ModBlocks;
import com.farmersoplenty.registry.ExternalItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Defines what each block drops (dropSelf by default).
 * getKnownBlocks() must list every block this provider generates a table for;
 * once you add blocks, return them all (e.g. from your DeferredRegister).
 */
public class BlockLootTables extends BlockLootSubProvider {

    protected BlockLootTables(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        ModBlocks.CABINETS.forEach(c -> dropSelf(c.get()));

        LootItemCondition.Builder mature = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.BARLEY_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties()
                        .hasProperty(CropBlock.AGE, 7));
        add(ModBlocks.BARLEY_CROP.get(), createCropDrops(ModBlocks.BARLEY_CROP.get(),
                ExternalItems.bop("barley"), ModBlocks.BARLEY_SEEDS.get(), mature));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return Stream.concat(ModBlocks.CABINETS.stream(), ModBlocks.CROPS.stream())
                .map(b -> (Block) b.get()).toList();
    }
}
