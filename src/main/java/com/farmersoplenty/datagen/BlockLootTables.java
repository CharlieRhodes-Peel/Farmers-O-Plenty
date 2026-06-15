package com.farmersoplenty.datagen;

import com.farmersoplenty.datagen.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Set;

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
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.CABINETS.stream().map(c -> (Block) c.get()).toList();
    }
}
