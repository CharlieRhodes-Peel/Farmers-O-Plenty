package com.farmersoplenty.registry;

import com.farmersoplenty.FarmersOPlenty;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * All custom tag keys in one place. Referenced both when declaring items
 * (ModItems) and when generating tag JSON (item/block tag providers).
 */
public final class ModTags {
    private ModTags() {}

    public static final class Items {
        public static final TagKey<Item> FOOD = tag("food");
        public static final TagKey<Item> MEALS = tag("meals");
        public static final TagKey<Item> SOUPS = tag("soups");
        public static final TagKey<Item> DRINKS = tag("drinks");
        public static final TagKey<Item> ANIMAL_FOODS = tag("animal_foods");
        public static final TagKey<Item> CABINETS = tag("cabinets");

        private static TagKey<Item> tag(String path) {
            return TagKey.create(Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(FarmersOPlenty.MODID, path));
        }
    }

    public static final class Blocks {
        public static final TagKey<Block> CABINETS = tag("cabinets");

        private static TagKey<Block> tag(String path) {
            return TagKey.create(Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(FarmersOPlenty.MODID, path));
        }
    }
}
