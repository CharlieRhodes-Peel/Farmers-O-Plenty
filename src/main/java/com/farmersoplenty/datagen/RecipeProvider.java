package com.farmersoplenty.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;

import java.util.concurrent.CompletableFuture;

/**
 * Generates all recipe JSON.
 * - Vanilla shaped/shapeless builders for crafting recipes (cabinets, flapjacks, salads).
 * - Farmer's Delight CookingPotRecipeBuilder / CuttingBoardRecipeBuilder for dishes & cutting.
 *   (FD 1.3 builders implement RecipeBuilder and support setNamespace(...) so results land
 *    under THIS mod's id.)
 *
 * NOTE: this is the 1.21.1 RecipeProvider shape (override buildRecipes).
 * If you ever update to 1.21.4+, this class changes to the RecipeProvider.Runner pattern.
 */
public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Step 4 example (Cutting Board):
        // CuttingBoardRecipeBuilder.cuttingRecipe(
        //         Ingredient.of(BopItems.ingredient("cattail")),
        //         Ingredient.of(Tags.Items.TOOLS_KNIVES_or_FD_knife_tag),
        //         ModItems.CHOPPED_CATTAIL.get())
        //     .build(recipeOutput);
        //
        // Step 4 example (Cooking Pot):
        // CookingPotRecipeBuilder.cookingPotRecipe(ModItems.CATTAIL_RICE_SOUP.get(), 1, 200, 0.0f, Items.BOWL)
        //     .addIngredient(...).addIngredient(...).addIngredient(...)
        //     .build(recipeOutput);
    }
}
