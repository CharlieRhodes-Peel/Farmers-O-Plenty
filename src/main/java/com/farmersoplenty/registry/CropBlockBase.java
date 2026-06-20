package com.farmersoplenty.registry;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

import java.util.function.Supplier;

/**
 * Reusable wheat-style crop. The only thing that varies per crop is the seed it relates to
 * (used for pick-block / the base seed), supplied lazily so block and seed can reference each other.
 * Growth, age stages (0-7), bonemeal and farmland rules all come from vanilla CropBlock.
 */
public class CropBlockBase extends CropBlock {
    private final Supplier<? extends ItemLike> seed;

    public CropBlockBase(Properties properties, Supplier<? extends ItemLike> seed) {
        super(properties);
        this.seed = seed;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return seed.get();
    }
}
