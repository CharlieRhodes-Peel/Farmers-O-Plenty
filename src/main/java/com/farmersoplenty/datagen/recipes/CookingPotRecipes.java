package com.farmersoplenty.datagen.recipes;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.registry.ExternalItems;
import com.farmersoplenty.registry.ModItems;
import com.farmersoplenty.registry.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

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
        // MEALS

        // Cattail Rice Soup
        Item cattail = ExternalItems.bop("cattail");

        mealCreator(ModItems.CATTAIL_RICE_SOUP.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Ingredient.of(cattail, ModItems.CHOPPED_CATTAIL.get()))
                .addIngredient(ExternalItems.fd("rice"))
                .addIngredient(ExternalItems.fd("onion"))
                .unlockedByItems("has_cattail", cattail)
                .save(output);

        // DRINKS

        // Lavender Honey Icecream
        Item milkBottle = ExternalItems.fd("milk_bottle");

        drinkCreator(ModItems.LAVENDER_HONEY_ICECREAM.get(), FAST_COOKING, SMALL_EXP)
                .addIngredient(ModTags.Items.LAVENDER)
                .addIngredient(ModTags.Items.HONEY)
                .addIngredient(milkBottle)
                .addIngredient(ModItems.ICE_CUBES)
                .unlockedByAnyIngredient(ExternalItems.bop("lavender"), ExternalItems.bop("white_lavender"), Items.ICE, Items.HONEY_BOTTLE, Items.HONEYCOMB, milkBottle)
                .save(output);


        //Beer
        Item barely = ExternalItems.bop("barley");
        drinkCreator(ModItems.BEER.get(), SLOW_COOKING, LARGE_EXP)
                .addIngredient(barely)
                .addIngredient(barely)
                .addIngredient(Ingredient.of(Items.WHEAT))
                .addIngredient(Ingredient.of(Items.SUGAR))
                .unlockedByAnyIngredient(barely)
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

    private static CookingPotRecipeBuilder drinkCreator(ItemLike result, int cookingTime, float xp){
        return CookingPotRecipeBuilder
                .cookingPotRecipe(result, 1, cookingTime, xp, Items.GLASS_BOTTLE)
                .setRecipeBookTab(CookingPotRecipeBookTab.DRINKS);
    }
}