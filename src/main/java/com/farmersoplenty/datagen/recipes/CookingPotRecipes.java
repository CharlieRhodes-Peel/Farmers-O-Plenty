package com.farmersoplenty.datagen.recipes;

import com.farmersoplenty.registry.ExternalItems;
import com.farmersoplenty.registry.ModItems;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

/**
 * All cooking pot recipes for Farmer's O' Plenty.
 *
 * Cook times:  FAST   = 100 ticks (5s)
 *              NORMAL = 200 ticks (10s)  <- standard for most dishes
 *              SLOW   = 400 ticks (20s)
 *
 * XP values:   SMALL  = 0.35f
 *              MEDIUM = 1.0f
 *              LARGE  = 2.0f
 */
public final class CookingPotRecipes {
    private CookingPotRecipes() {}

    // Konstantins
    public static final int FAST_COOKING = 100;      // 5 seconds
    public static final int NORMAL_COOKING = 200;    // 10 seconds
    public static final int SLOW_COOKING = 400;      // 20 seconds

    public static final float SMALL_EXP = 0.35F;
    public static final float MEDIUM_EXP = 1.0F;
    public static final float LARGE_EXP = 2.0F;

    // -------------------------------------------------------------------------
    // Recipes
    // -------------------------------------------------------------------------

    public static void register(RecipeOutput output) {
        // Cattail Rice Soup
        Item cattail = ExternalItems.bop("cattail");

        mealCreator(ModItems.CATTAIL_RICE_SOUP.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Ingredient.of(cattail, ModItems.CHOPPED_CATTAIL.get()))
                .addIngredient(ExternalItems.fd("rice"))
                .addIngredient(ExternalItems.fd("onion"))
                .unlockedByItems("has_cattail", cattail)
                .save(output);
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    /** Served in a bowl (most soups and stews). Tab: MEALS. */
    private static CookingPotRecipeBuilder mealCreator(ItemLike result, int cookingTime, float xp){
        return CookingPotRecipeBuilder
                .cookingPotRecipe(result, 1, cookingTime, xp, Items.BOWL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS);
    }
}