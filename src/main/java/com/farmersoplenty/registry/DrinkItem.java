package com.farmersoplenty.registry;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.jetbrains.annotations.NotNull;

public class DrinkItem extends EffectTooltipItem{
    public DrinkItem(Properties properties){
        super(properties);
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack){
        return UseAnim.DRINK;
    }
}
