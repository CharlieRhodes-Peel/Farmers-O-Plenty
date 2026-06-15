package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import com.farmersoplenty.registry.ModItems;
import com.farmersoplenty.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * Generates item tag JSON (data/.../tags/item/*.json).
 * This is where FOOD / MEALS / SOUPS / Drinks / Cabinets item tags get populated.
 * The blockTags future lets you copy() a block tag into the matching item tag when needed.
 */
public class ItemTagsProvider extends net.minecraft.data.tags.ItemTagsProvider {

    public ItemTagsProvider(PackOutput output,
                            CompletableFuture<HolderLookup.Provider> lookupProvider,
                            CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
                            @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, FarmersOPlenty.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        ModItems.TAGGED.forEach((tagKey, items) -> {
            var appender = tag(tagKey);
            items.forEach(item -> appender.add(item.get()));
        });

        copy(ModTags.Blocks.CABINETS, ModTags.Items.CABINETS);
    }
}
