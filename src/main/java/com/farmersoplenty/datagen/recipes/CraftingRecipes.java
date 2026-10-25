package com.farmersoplenty.datagen.recipes;

import com.farmersoplenty.registry.ExternalItems;
import com.farmersoplenty.datagen.ModBlocks;

import com.farmersoplenty.registry.ModItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.RecipeCategory;
//? if >=1.21 {
import net.minecraft.data.recipes.RecipeOutput;
//?} else {
/*import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;
*///?}
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

// Recipes are written to a RecipeOutput since 1.20.2; before that it's a Consumer<FinishedRecipe>.
//~ if <1.21 'RecipeOutput' -> 'Consumer<FinishedRecipe>' {
/**
 * Vanilla shaped/shapeless crafting recipes (cabinets now; flapjacks, salads later).
 */
public final class CraftingRecipes {
    private CraftingRecipes() {}

    public static void register(RecipeOutput output) {
        cabinet(output, ModBlocks.FIR_CABINET.get(), "fir");
        cabinet(output, ModBlocks.PINE_CABINET.get(), "pine");
        cabinet(output, ModBlocks.MAPLE_CABINET.get(), "maple");
        cabinet(output, ModBlocks.REDWOOD_CABINET.get(), "redwood");
        cabinet(output, ModBlocks.MAHOGANY_CABINET.get(), "mahogany");
        cabinet(output, ModBlocks.JACARANDA_CABINET.get(), "jacaranda");
        cabinet(output, ModBlocks.PALM_CABINET.get(), "palm");
        cabinet(output, ModBlocks.WILLOW_CABINET.get(), "willow");
        cabinet(output, ModBlocks.DEAD_CABINET.get(), "dead");
        cabinet(output, ModBlocks.MAGIC_CABINET.get(), "magic");
        cabinet(output, ModBlocks.UMBRAN_CABINET.get(), "umbran");
        cabinet(output, ModBlocks.HELLBARK_CABINET.get(), "hellbark");
        cabinet(output, ModBlocks.EMPYREAL_CABINET.get(), "empyreal");

        //Honey Flapjack
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.HONEY_FLAPJACK.get())
                .requires(ModItems.CRUSHED_OATS.get(), 2)
                .requires(Items.HONEY_BOTTLE)
                .requires(Items.SUGAR)
                .unlockedBy("has_crushed_oats", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CRUSHED_OATS))
                .save(output);

        //Berry Oat Bakewell

    }

    /*
    BoP Wood Order:
    Fir
    Pine
    Maple
    Redwood
    Mahogany
    Jacaranda
    Palm
    Willow
    Dead
    Magic
    Umbran
    Hellbark
    Empyreal
     */




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
//~}
