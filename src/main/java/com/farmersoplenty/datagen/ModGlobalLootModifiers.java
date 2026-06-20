package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.datagen.loot.AddItemModifier;
import com.farmersoplenty.datagen.ModBlocks;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiers extends GlobalLootModifierProvider {

    public ModGlobalLootModifiers(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, FarmersOPlenty.MODID);
    }

    @Override
    protected void start() {
        // When BoP barley is broken by hand (not sheared), 1-in-8 chance to also drop our seeds.
        // BoP's own table already gives the barley plant only when sheared, so the two paths don't overlap.
        add("barley_seeds_from_bop_barley",
                new AddItemModifier(new LootItemCondition[]{
                        LootTableIdCondition.builder(
                                ResourceLocation.fromNamespaceAndPath("biomesoplenty", "blocks/barley")).build(),
                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS)).invert().build(),
                        LootItemRandomChanceCondition.randomChance(0.125F).build()
                }, ModBlocks.BARLEY_SEEDS.get()),
                List.of());
    }
}
