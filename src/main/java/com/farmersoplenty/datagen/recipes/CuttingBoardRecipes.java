package com.farmersoplenty.datagen.recipes;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.registry.ExternalItems;
import com.farmersoplenty.registry.ModItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

/**
 * All cutting board recipes for Farmer's O' Plenty.
 * Pattern: Chopping(input, output, tool, amount).save(output) !! THAT'S IT! !!
 */
public final class CuttingBoardRecipes {
    private CuttingBoardRecipes() {}

    // -------------------------------------------------------------------------
    // Recipes
    // -------------------------------------------------------------------------

    public static void register(RecipeOutput output) {
        // Cattail -> Chopped Cattail (x2)
        Chopping(
                ExternalItems.bop("cattail"),
                ModItems.CHOPPED_CATTAIL.get(),
                ExternalItems.KNIVES,
                2
        ).save(output);

        // Any ice -> Ice cubes
        IceCubeFrom(Items.ICE, 2).save(output);
        IceCubeFrom(Items.PACKED_ICE, 9 * 2).save(output);
        IceCubeFrom(Items.BLUE_ICE, 9 * 4).save(output);

        //Crushed oats
        Chopping(
                ExternalItems.bop("sea_oats"),
                ModItems.CRUSHED_OATS.get(),
                ExternalItems.KNIVES,
                2
        ).save(output);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private static CuttingBoardRecipeBuilder Chopping(ItemLike input, ItemLike output, TagKey<Item> tool, int amount){
        return CuttingBoardRecipeBuilder
                .cuttingRecipe(
                    Ingredient.of(input),
                    Ingredient.of(tool),
                    output,
                    amount
        )
                .setNamespace(FarmersOPlenty.MODID);
    }

    //Helpful wrapper so I don't have to write much for different Ice types
    private static CuttingBoardRecipeBuilder IceCubeFrom(ItemLike input, int amount){
        return Chopping(input, ModItems.ICE_CUBES.get(), ExternalItems.KNIVES, amount);
    }

}