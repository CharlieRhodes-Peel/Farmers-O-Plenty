package com.farmersoplenty.registry;

import com.farmersoplenty.FarmersOPlenty;

import net.minecraft.core.Holder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * SINGLE SOURCE OF TRUTH for every item this mod adds.
 *
 * Declaring an item here also drives data generation automatically:
 *   - basic item model is generated      (item-model provider reads ENTRIES)
 *   - en_us name derived from the path   (lang provider reads ENTRIES + overrides)
 *   - added to the creative tab          (ModCreativeTabs reads ENTRIES)
 *   - declared tags are emitted          (item-tag provider reads TAGGED)
 *   - food(...) auto-applies FOOD        (the README's "all edibles are FOOD" rule)
 *
 * Net result: most items are ONE line. Only recipes live elsewhere (recipe provider),
 * because recipe contents are unique per item rather than duplicated metadata.
 */
public final class ModItems {
    private ModItems() {}

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FarmersOPlenty.MODID);

    // ---- Datagen metadata, captured as items are declared (must sit ABOVE the content below) ----
    /** Every declared item, in declaration order (= creative tab order). */
    public static final List<DeferredItem<? extends Item>> ENTRIES = new ArrayList<>();
    /** Display-name overrides for names the auto title-caser can't get right (apostrophes etc.). */
    public static final Map<DeferredItem<Item>, String> DISPLAY_NAME_OVERRIDES = new LinkedHashMap<>();
    /** tag -> items belonging to it. */
    public static final Map<TagKey<Item>, List<DeferredItem<Item>>> TAGGED = new LinkedHashMap<>();

    // =====================================================================================
    //  CONTENT  (step 3+ goes here — keep declarations BELOW the maps above)
    // =====================================================================================

    // ------- Ingredients ------
    public static final DeferredItem<Item> CHOPPED_CATTAIL = animalFood("chopped_cattail");
    public static final DeferredItem<Item> ICE_CUBES = item("ice_cubes");
    public static final DeferredItem<Item> CRUSHED_OATS = item("crushed_oats");
    public static final DeferredItem<Item> WATERLILY_STEMS = item("waterlily_stems");

    // -------- Dishes -------
    // Soups
    public static final DeferredItem<Item> CATTAIL_RICE_SOUP = food("cattail_rice_soup",
            foodStats(8,3f, Items.BOWL, createEffect(ModEffects.NOURISHMENT, 3)),
            ModTags.Items.MEALS,
            ModTags.Items.SOUPS
    );

    public static final DeferredItem<Item> NETHER_SOUP = food("nether_soup",
            foodStats(9, 3f, Items.BOWL, createEffect(ModEffects.NOURISHMENT, 5)),
            ModTags.Items.MEALS,
            ModTags.Items.SOUPS);

    // Bottled
    public static final DeferredItem<Item> LAVENDER_HONEY_ICECREAM = food("lavender_honey_icecream",
            foodStats(8, 3f, Items.GLASS_BOTTLE, createEffect(ModEffects.NOURISHMENT, 3)),
            ModTags.Items.DRINKS);

    // ----------- Misc --------
    public static final DeferredItem<Item> BEER = drink("beer",
            drinkStats(4, 3f, Items.GLASS_BOTTLE, createEffect(MobEffects.ABSORPTION, 1)),
            ModTags.Items.DRINKS);

    public static final DeferredItem<Item> HONEY_FLAPJACK = food("honey_flapjack",
            foodStats(6, 3f));

    // =====================================================================================
    //  HELPERS
    // =====================================================================================

    /** A plain (non-food) item, e.g. an ingredient. */
    @SafeVarargs
    public static DeferredItem<Item> item(String name, TagKey<Item>... tags) {
        return register(name, Item::new, tags);
    }

    /** A food item. Auto-tagged FOOD, plus any extra tags (MEALS, SOUPS, DRINKS...). */
    @SafeVarargs
    public static DeferredItem<Item> food(String name, FoodProperties food, TagKey<Item>... extraTags) {
        DeferredItem<Item> item = register(name,
                props -> new EffectTooltipItem(props.food(food)), extraTags);
        addTag(ModTags.Items.FOOD, item);
        return item;
    }

    @SafeVarargs
    public static DeferredItem<Item> drink(String name, FoodProperties drink, TagKey<Item>... extraTags){
        DeferredItem<Item> item = register(name,
                props -> new DrinkItem(props.food(drink)), extraTags);
        addTag(ModTags.Items.FOOD, item);
        addTag(ModTags.Items.DRINKS, item);
        return item;
    }

    /** Ingredient that also goes in ANIMAL_FOODS (the README's chopped/crushed items). */
    @SafeVarargs
    public static DeferredItem<Item> animalFood(String name, TagKey<Item>... extraTags) {
        DeferredItem<Item> item = item(name, extraTags);
        addTag(ModTags.Items.ANIMAL_FOODS, item);
        return item;
    }

    // ----------- Food Stats + Overloads --------
    public static FoodProperties foodStats(int nutrition, float saturation){
        return new FoodProperties.Builder().nutrition(nutrition).saturationModifier(saturation).build();
    }

    //Returns Container on use
    public static FoodProperties foodStats(int nutrition, float saturation, ItemLike container) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .usingConvertsTo(container)
                .build();
    }

    //Applied A potion effect and return container on use
    public static FoodProperties foodStats(int nutrition, float saturation, ItemLike container, Supplier<MobEffectInstance> potionEffect) {
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .usingConvertsTo(container)
                .effect(potionEffect, 1.0f)
                .build();
    }

    //Same as food stats but allows it to be drunk even when hunger is full
    public static FoodProperties drinkStats(int nutrition, float saturation, ItemLike container, Supplier<MobEffectInstance> potionEffect){
        return new FoodProperties.Builder()
                .nutrition(nutrition)
                .saturationModifier(saturation)
                .usingConvertsTo(container)
                .effect(potionEffect, 1.0f)
                .alwaysEdible()
                .build();
    }

    //Type changing helper
    private static Supplier<MobEffectInstance> createEffect(Holder<MobEffect> effect, int minutes){
        return () -> new MobEffectInstance(effect, minutes * 1200); // * 1200 to convert minutes -> ticks
    }

    /** Attach an explicit display name (use when the auto title-caser won't do). */
    public static DeferredItem<Item> named(DeferredItem<Item> item, String displayName) {
        DISPLAY_NAME_OVERRIDES.put(item, displayName);
        return item;
    }

    public static DeferredItem<BlockItem> blockItem(DeferredBlock<? extends Block> block){
        DeferredItem<BlockItem> item = ITEMS.registerSimpleBlockItem(block);
        ENTRIES.add(item);
        return item;
    }

    @SafeVarargs
    private static DeferredItem<Item> register(String name,
                                               java.util.function.Function<Item.Properties, Item> factory,
                                               TagKey<Item>... tags) {
        DeferredItem<Item> item = ITEMS.register(name, () -> factory.apply(new Item.Properties()));
        ENTRIES.add(item);
        for (TagKey<Item> tag : tags) addTag(tag, item);
        return item;
    }

    private static void addTag(TagKey<Item> tag, DeferredItem<Item> item) {
        TAGGED.computeIfAbsent(tag, k -> new ArrayList<>()).add(item);
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
