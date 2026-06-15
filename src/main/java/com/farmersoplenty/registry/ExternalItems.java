package com.farmersoplenty.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * References to items and tags owned by OTHER mods (Biomes O' Plenty, Farmer's Delight),
 * resolved by ResourceLocation instead of by importing their classes.
 *
 * Why by ID rather than BOPItems.CATTAIL / FD's ModItems?
 *  - BOP exposes no stable item API; touching its internals risks breaking on their updates.
 *  - FD's own ModItems/ModTags are documented as "not for add-ons, subject to change" — FD
 *    recommends exactly these kinds of local references.
 *  - One uniform pattern for every external mod, with no class-name clash against our ModItems.
 *
 * Resolution happens at datagen time (after every mod has registered) and throws immediately if
 * an id is missing, so a typo or a missing dependency fails loudly instead of silently giving AIR.
 */
public final class ExternalItems {
    private ExternalItems() {}

    public static final String BOP = "biomesoplenty";
    public static final String FD  = "farmersdelight";

    /** Farmer's Delight knives tag — local reference, per FD's add-on guidance. */
    public static final TagKey<Item> KNIVES =
            TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(FD, "tools/knives"));

    public static Item item(String namespace, String path) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(namespace, path);
        Item item = BuiltInRegistries.ITEM.get(id);
        if (item == Items.AIR) {
            throw new IllegalStateException(
                    "External item '" + id + "' was not found during data generation. " +
                    "Is the owning mod on the datagen classpath?");
        }
        return item;
    }

    public static Item bop(String path) { return item(BOP, path); }
    public static Item fd(String path)  { return item(FD, path); }

    public static Ingredient ingredient(String namespace, String path) {
        return Ingredient.of(item(namespace, path));
    }
}
