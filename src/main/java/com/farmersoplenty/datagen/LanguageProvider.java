package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.registry.ModItems;
import net.minecraft.data.PackOutput;

/**
 * Generates assets/farmersoplenty/lang/en_us.json.
 * add(Item, "Name") / add(Block, "Name") here for every new content entry.
 */
public class LanguageProvider extends net.neoforged.neoforge.common.data.LanguageProvider {

    public LanguageProvider(PackOutput output) {
        super(output, FarmersOPlenty.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + FarmersOPlenty.MODID, "Farmer's O' Plenty");
        ModItems.ENTRIES.forEach(item -> {
            String override = ModItems.DISPLAY_NAME_OVERRIDES.get(item);
            add(item.get(), override != null ? override : toDisplayName(item.getId().getPath()));
        });
    }

    private static final java.util.Set<String> SMALL_WORDS =
            java.util.Set.of("a", "an", "and", "of", "or", "the", "with", "in", "on");

    private static String toDisplayName(String path) {
        String[] parts = path.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) sb.append(' ');
            String w = parts[i];
            boolean small = i > 0 && i < parts.length - 1 && SMALL_WORDS.contains(w);
            sb.append(small ? w : Character.toUpperCase(w.charAt(0)) + w.substring(1));
        }
        return sb.toString();
    }
}
