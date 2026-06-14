package com.farmersoplenty.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Builds the LootTableProvider. Only the cabinet blocks need loot tables
 * (so they drop themselves when broken); food items don't.
 */
public class LootTableProvider {

    public static net.minecraft.data.loot.LootTableProvider create(PackOutput output,
                                                                   CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return new net.minecraft.data.loot.LootTableProvider(
                output,
                Set.of(), // required table IDs to validate against; empty for now
                List.of(new net.minecraft.data.loot.LootTableProvider.SubProviderEntry(
                        BlockLootTables::new, LootContextParamSets.BLOCK)),
                lookupProvider);
    }
}
