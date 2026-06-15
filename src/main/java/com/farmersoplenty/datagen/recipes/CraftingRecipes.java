package com.farmersoplenty.datagen.recipes;

import com.farmersoplenty.registry.ExternalItems;
import com.farmersoplenty.datagen.ModBlocks;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.ItemLike;

/**
 * Vanilla shaped/shapeless crafting recipes (cabinets now; flapjacks, salads later).
 */
public final class CraftingRecipes {
    private CraftingRecipes() {}

    public static void register(RecipeOutput output) {
        cabinet(output, ModBlocks.FIR_CABINET.get(), "fir");
        cabinet(output, ModBlocks.FIR_CABINET.get(), "willow");
    }

    private static void cabinet(RecipeOutput output, ItemLike result, String bopWood) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .pattern("SSS")
                .pattern("D D")
                .pattern("SSS")
                .define('S', ExternalItems.bop(bopWood + "_slab"))
                .define('D', ExternalItems.bop(bopWood + "_trapdoor"))
                .unlockedBy("has_" + bopWood + "_trapdoor",
                        InventoryChangeTrigger.TriggerInstance.hasItems(ExternalItems.bop(bopWood + "_trapdoor")))
                .group("cabinet")
                .save(output);
    }
}
