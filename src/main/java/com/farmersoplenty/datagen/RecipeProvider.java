package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.registry.ExternalItems;
import com.farmersoplenty.registry.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

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

    // Konstantins
    public static final int FAST_COOKING = 100;      // 5 seconds
    public static final int NORMAL_COOKING = 200;    // 10 seconds
    public static final int SLOW_COOKING = 400;      // 20 seconds

    public static final float SMALL_EXP = 0.35F;
    public static final float MEDIUM_EXP = 1.0F;
    public static final float LARGE_EXP = 2.0F;


    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // ==================================================
        // Cutting Board Recipes
        // ==================================================
        CuttingBoardRecipeBuilder
                .cuttingRecipe(
                        Ingredient.of(ExternalItems.bop("cattail")),        // Input
                        Ingredient.of(ExternalItems.KNIVES),                     // Tool
                        ModItems.CHOPPED_CATTAIL.get(),                          // Output
                        2                                                        // Output Amount
                )
                .setNamespace(FarmersOPlenty.MODID) // input is a BOP item, so force OUR namespace on the recipe id
                .save(recipeOutput);

        // ==================================================
        // Cooking Pot Recipes
        // ==================================================

        // Cattail Rice Soup
        Item cattail = ExternalItems.bop("cattail");

        mealCreator(ModItems.CATTAIL_RICE_SOUP.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Ingredient.of(cattail, ModItems.CHOPPED_CATTAIL.get()))
                .addIngredient(ExternalItems.fd("rice"))
                .addIngredient(ExternalItems.fd("onion"))
                .unlockedByItems("has_cattail", cattail).save(recipeOutput);
    }

    // ----------- Helper Functions ---------

    private static CookingPotRecipeBuilder mealCreator(ItemLike result, int cookingTime, float xp){
        return CookingPotRecipeBuilder
                .cookingPotRecipe(result, 1, cookingTime, xp, Items.BOWL)
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS);
    }
}
