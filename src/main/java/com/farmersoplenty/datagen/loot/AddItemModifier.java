package com.farmersoplenty.datagen.loot;

//? if neoforge {
import com.mojang.serialization.MapCodec;
//?} else
//import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
//? if neoforge {
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
//?} else {
/*import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
*///?}

/**
 * A reusable global loot modifier: adds one item to a loot table's drops when ALL of its
 * conditions pass. The "when" (which table, what chance, which tool) lives entirely in the
 * conditions array; this class just appends the item. Reuse it for any "drop X from Y" need.
 */
public class AddItemModifier extends LootModifier {

    //? if neoforge {
    public static final MapCodec<AddItemModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
    //?} else
    //public static final Codec<AddItemModifier> CODEC = RecordCodecBuilder.create(inst ->
            codecStart(inst)
                    .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(m -> m.item))
                    .apply(inst, AddItemModifier::new));

    private final Item item;

    public AddItemModifier(LootItemCondition[] conditions, Item item) {
        super(conditions);
        this.item = item;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(item));
        return generatedLoot;
    }

    @Override
    //? if neoforge {
    public MapCodec<? extends IGlobalLootModifier> codec() {
    //?} else
    //public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
