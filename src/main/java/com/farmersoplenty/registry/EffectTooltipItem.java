package com.farmersoplenty.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
//? if <1.21 {
/*import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
*///?}
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

    //? if >=1.21 {
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltip, flag);
        TextUtils.addFoodEffectTooltip(stack, tooltip::add, 1.0f, context.tickRate());
    }
    //?} else {
    /*@Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        TextUtils.addFoodEffectTooltip(stack, tooltip, 1.0f);
    }

    // 1.20.1 food can't convert into a container (usingConvertsTo is 1.21+), so ModItems stores the
    // bowl/bottle as the craft remainder and it is handed back here, the same way FD's ConsumableItem does it.
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity consumer) {
        ItemStack container = stack.getCraftingRemainingItem();
        ItemStack result = super.finishUsingItem(stack, level, consumer);
        if (container.isEmpty()) {
            return result;
        }
        if (result.isEmpty()) {
            return container;
        }
        if (consumer instanceof Player player && !player.getAbilities().instabuild && !player.getInventory().add(container)) {
            player.drop(container, false);
        }
        return result;
    }
    *///?}
}
