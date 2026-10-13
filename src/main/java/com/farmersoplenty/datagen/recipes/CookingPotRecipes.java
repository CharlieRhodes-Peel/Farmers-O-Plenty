package com.farmersoplenty.datagen.recipes;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.registry.ExternalItems;
import com.farmersoplenty.registry.ModItems;
import com.farmersoplenty.registry.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.nbt.Tag;
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

import java.awt.*;

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
        // ---------- MEALS ----------

        // Soups
        // Cattail Rice Soup
        Item cattail = ExternalItems.bop("cattail");

        mealCreator(ModItems.CATTAIL_RICE_SOUP.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Ingredient.of(cattail, ModItems.CHOPPED_CATTAIL.get()))
                .addIngredient(ExternalItems.fd("rice"))
                .addIngredient(ExternalItems.fd("onion"))
                .unlockedByItems("has_cattail", cattail)
                .save(output);

        mealCreator(ModItems.NETHER_SOUP.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(ExternalItems.bop("bramble"))
                .addIngredient(ExternalItems.bop("eyebulb"))
                .addIngredient(ExternalItems.fd("nether_salad"))
                .addIngredient(ExternalItems.bop("blood_bucket"))
                .unlockedByAnyIngredient(ExternalItems.bop("bramble"), ExternalItems.bop("eyebulb"))
                .save(output);

        mealCreator(ModItems.SOUP_OF_ENDER.get(), SLOW_COOKING, LARGE_EXP)
                .addIngredient(Items.ENDER_EYE)
                .addIngredient(Items.CHORUS_FRUIT)
                .addIngredient(Items.CHORUS_FRUIT)
                .addIngredient(ExternalItems.bop("lumaloop"))
                .save(output);

        mealCreator(ModItems.WATERLILY_MIX.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(ModItems.WATERLILY_STEMS.get())
                .addIngredient(ExternalItems.fd("onion"))
                .addIngredient(Items.POTATO)
                .addIngredient(Items.SALMON)
                .unlockedByAnyIngredient(ExternalItems.fd("onion"), ExternalItems.bop("waterlily"))
                .save(output);

        mealCreator(ModItems.CLOVER_BLOSSOM_SALAD.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(ModItems.GLOWFLOWER_BERRY)
                .addIngredient(ExternalItems.bop("clover"))
                .addIngredient(Items.BEETROOT)
                .addIngredient(ModTags.Items.CABBAGE)
                .unlockedByAnyIngredient(ModItems.GLOWFLOWER_BERRY, ExternalItems.bop("clover"), Items.BEETROOT)
                .save(output);

        mealCreator(ModItems.BERRY_OAT_BAKEWELL.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.SWEET_BERRIES)
                .addIngredient(ModItems.GLOWFLOWER_BERRY)
                .addIngredient(ModItems.CRUSHED_OATS)
                .addIngredient(ModItems.CRUSHED_OATS)
                .unlockedByAnyIngredient(Items.GLOW_BERRIES, Items.SWEET_BERRIES, ModItems.CRUSHED_OATS, ModItems.GLOWFLOWER_BERRY)
                .save(output);

        mealCreator(ModItems.PORK_CACTUS_RICE.get(), NORMAL_COOKING, MEDIUM_EXP)
                .addIngredient(ModTags.Items.PORK)
                .addIngredient(ModTags.Items.CACTUS)
                .addIngredient(ExternalItems.fd("rice"))
                .unlockedByAnyIngredient(Items.PORKCHOP, Items.COOKED_PORKCHOP, ExternalItems.bop("tiny_cactus"), Items.CACTUS, ExternalItems.fd("rice"))
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

        drinkCreator(ModItems.GLOW_GLOW_JUICE.get(), FAST_COOKING, SMALL_EXP)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(Items.GLOW_BERRIES)
                .addIngredient(ModItems.GLOWFLOWER_BERRY)
                .addIngredient(Items.SUGAR)
                .addIngredient(ModItems.ICE_CUBES)
                .unlockedByAnyIngredient(Items.GLOW_BERRIES, Items.SUGAR, Items.ICE, ModItems.GLOWFLOWER_BERRY)
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