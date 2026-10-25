package com.farmersoplenty.registry;

import com.farmersoplenty.FarmersOPlenty;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CartographyTableBlock;

import javax.swing.text.html.HTML;

/**
 * All custom tag keys in one place. Referenced both when declaring items
 * (ModItems) and when generating tag JSON (item/block tag providers).
 */
public final class ModTags {
    private ModTags() {}

    public static final class Items {
        public static final TagKey<Item> FOOD = cTag("foods");
        public static final TagKey<Item> MEALS = modTag("meals");
        public static final TagKey<Item> SOUPS = modTag("soups");
        public static final TagKey<Item> DRINKS = cTag("drinks");
        public static final TagKey<Item> ANIMAL_FOODS = modTag("animal_foods");
        public static final TagKey<Item> CABINETS = modTag("cabinets");
        public static final TagKey<Item> SEEDS = cTag("seeds");
        public static final TagKey<Item> CROPS = cTag("crops");
        //? if >=1.21 {
        public static final TagKey<Item> CABBAGE = cTag("foods/cabbage");
        //?} else
        //public static final TagKey<Item> CABBAGE = cTag("crops/cabbage");

        //Stuff for Cooking pot / Crafting
        public static final TagKey<Item> LAVENDER = modTag("lavender");
        public static final TagKey<Item> HONEY = modTag("honey");
        public static final TagKey<Item> PORK = modTag("pork");
        public static final TagKey<Item> CACTUS = modTag("cactus");

        private static TagKey<Item> modTag(String path) {
            return TagKey.create(Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(FarmersOPlenty.MODID, path));
        }

        /** Common (cross-mod) tag: "c:" since 1.21, "forge:" on Forge 1.20.1. */
        private static TagKey<Item> cTag(String path) {
            //? if >=1.21 {
            String namespace = "c";
            //?} else
            //String namespace = "forge";
            return TagKey.create(Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath(namespace, path));
        }
    }

    public static final class Blocks {
        public static final TagKey<Block> CABINETS = modTag("cabinets");

        private static TagKey<Block> modTag(String path) {
            return TagKey.create(Registries.BLOCK,
                    ResourceLocation.fromNamespaceAndPath(FarmersOPlenty.MODID, path));
        }
    }
}
