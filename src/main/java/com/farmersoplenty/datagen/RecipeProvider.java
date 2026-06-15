package com.farmersoplenty.datagen;

import com.farmersoplenty.datagen.recipes.CookingPotRecipes;
import com.farmersoplenty.datagen.recipes.CraftingRecipes;
import com.farmersoplenty.datagen.recipes.CuttingBoardRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;

import java.util.concurrent.CompletableFuture;

/**
 * Entry point for all recipe generation.
 * Delegates to per-type sub-files in datagen/recipes/.
 * Add a new category? Create a new sub-file and call it here.
 *
 * NOTE: this is the 1.21.1 RecipeProvider shape (override buildRecipes).
 * If you ever update to 1.21.4+, this changes to the RecipeProvider.Runner pattern.
 */
public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {

    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        CuttingBoardRecipes.register(output);
        CookingPotRecipes.register(output);
        CraftingRecipes.register(output);
    }
}