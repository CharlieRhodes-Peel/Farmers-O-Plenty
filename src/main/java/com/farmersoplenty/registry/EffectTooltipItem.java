package com.farmersoplenty.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.List;

/**
 * A plain Item that renders food effect tooltips using FD's TextUtils utility.
 * Used for any item with effects declared in FoodProperties (e.g. Nourishment).
 * Does NOT extend ConsumableItem — FD's container-return logic would conflict
 * with the usingConvertsTo we set on FoodProperties.
 */
public class EffectTooltipItem extends Item {

    public EffectTooltipItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0f, context.tickRate());
    }
}