package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

/**
 * Single entry point for all data generation.
 * Fires during the `runData` gradle task and hands each provider to the DataGenerator.
 *
 * Client providers (assets: models, lang) are gated behind includeClient().
 * Server providers (data: tags, loot, recipes) are gated behind includeServer().
 */
@EventBusSubscriber(modid = FarmersOPlenty.MODID)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // ----- Client assets -----
        generator.addProvider(event.includeClient(),
                new ItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(),
                new BlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(),
                new LanguageProvider(packOutput));
        generator.addProvider(event.includeServer(),
                new ModGlobalLootModifiers(packOutput, lookupProvider));

        // ----- Server data -----
        // Block tags must be built first so item tags can copy block->item where needed.
        BlockTagsProvider blockTags =
                new BlockTagsProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);

        generator.addProvider(event.includeServer(),
                new ItemTagsProvider(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(),
                LootTableProvider.create(packOutput, lookupProvider));

        generator.addProvider(event.includeServer(),
                new RecipeProvider(packOutput, lookupProvider));
    }
}