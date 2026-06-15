package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import com.farmersoplenty.datagen.ModBlocks;
import com.farmersoplenty.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Generates block tag JSON (data/.../tags/block/*.json).
 * Provides Tags to blocks e.g MINEABLE_WITH_AXE
 */
public class BlockTagsProvider extends net.neoforged.neoforge.common.data.BlockTagsProvider {

    public BlockTagsProvider(PackOutput output,
                             CompletableFuture<HolderLookup.Provider> lookupProvider,
                             @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FarmersOPlenty.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        var cab = tag(ModTags.Blocks.CABINETS);
        var axe = tag(BlockTags.MINEABLE_WITH_AXE);
        ModBlocks.CABINETS.forEach(c -> { cab.add(c.get()); axe.add(c.get());});
    }
}
