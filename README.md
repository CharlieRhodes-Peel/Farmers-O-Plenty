# Farmer's O' Plenty

**A compatibility mod that brings [Biomes O' Plenty](https://www.curseforge.com/minecraft/mc-mods/biomes-o-plenty) and [Farmer's Delight](https://www.curseforge.com/minecraft/mc-mods/farmers-delight) together.**

Biomes O' Plenty fills your world with new plants, but most of them are just decoration. Farmer's O' Plenty turns them into ingredients with the help of Farmer's Delight. Chop cattails, grind sea oats, farm barley, brew beer, and cook new dishes in the cooking pot, all made from what you find in BoP's biomes.

## Requirements

| Dependency | Version |
| --- | --- |
| Minecraft + loader | 1.21.1 with NeoForge 21.1.x, **or** 1.20.1 with Forge 47.x |
| [Biomes O' Plenty](https://www.curseforge.com/minecraft/mc-mods/biomes-o-plenty) | required |
| [Farmer's Delight](https://www.curseforge.com/minecraft/mc-mods/farmers-delight) | required |

Install all three alongside this mod. It adds nothing to the world on its own and only works as a bridge between the two.

## What's included

### Barley, now farmable

Biomes O' Plenty's barley can only be collected with shears, and you can't plant it. This mod fixes that.

- **Find your first seeds.** Break wild barley by hand (not with shears) and it has a chance to drop **Barley Seeds**, much like tall grass drops wheat seeds.
- **Plant and grow.** Barley Seeds plant on farmland and grow through eight stages, and bone meal works.
- **Harvest.** A fully grown crop drops Biomes O' Plenty's own **Barley** plus more seeds, and Fortune increases the seed yield.

### Cabinets for every Biomes O' Plenty wood

Farmer's Delight cabinets, in all 13 of Biomes O' Plenty's wood types. They work and craft like the originals: 6 slabs and 2 trapdoors of the matching wood.

Fir, Pine, Maple, Redwood, Mahogany, Jacaranda, Palm, Willow, Dead, Magic, Umbran, Hellbark and Empyreal.

### New ingredients

Use a knife on the cutting board to turn Biomes O' Plenty plants into cooking ingredients.

| Ingredient | Made by chopping |
| --- | --- |
| Chopped Cattail | Cattail |
| Crushed Oats | Sea Oats |
| Waterlily Stem | Waterlily |
| Glowflower Berry | Glowflower |
| Ice Cubes | Any ice |

### New dishes

Cook these in the Farmer's Delight cooking pot.

| Dish | Ingredients |
| --- | --- |
| Cattail Rice Soup | Cattail or Chopped Cattail + Rice + Onion |
| Lavender Honey Ice Cream | Lavender + Honey Bottle + Milk Bottle + Ice |
| Nether Soup | Bramble + Eyebulb + Blaze Powder + Nether Salad |
| Berry Oat Bakewell | Glow Berry + Sweet Berry + Crushed Oats |
| Pork and Cactus Rice | Raw Pork or Bacon + Cactus or Tiny Cactus + Rice |
| Waterlily Mix | Salmon + Onion + Waterlily Stem + Potato |
| Soup of Ender | Eye of Ender + 2 Chorus Fruit + Lumaloop |
| Clover Blossom Salad | Clover + Cabbage + Beetroot + Glowflower Berry |

### Drinks and sweets

| Item | How it's made | Effect |
| --- | --- | --- |
| Beer | 2 Barley + Wheat + Sugar, served in a Glass Bottle. It takes a long time to brew. | Absorption (1:00) |
| Glow Glow Juice | 2 Glow Berries + Glowflower Berry + Sugar + Ice, served in a Glass Bottle | Glowing (1:00) |
| Honey Flapjacks | Crafted, not cooked: 2 Crushed Oats + Honey Bottle + Sugar. Makes a batch. | Quick snack |

### Good for the garden

The new ingredients and baked goods can go in a composter. All edible items are tagged as food, so they work with other mods' food mechanics.

## Compatibility

- Designed around Farmer's Delight's cooking pot, cutting board and cabinets, so everything works with its recipe book and JEI.
- Uses Biomes O' Plenty's own items (Barley, Cattail, Lavender and the rest), so you never end up with duplicate versions of a plant.
- Everything is data-driven, so modpack makers can adjust recipes with a datapack.

## Credits

- [Biomes O' Plenty](https://www.curseforge.com/minecraft/mc-mods/biomes-o-plenty) by Glitchfiend
- [Farmer's Delight](https://www.curseforge.com/minecraft/mc-mods/farmers-delight) by vectorwing

## Building from source

One source tree builds every supported version, using [Stonecutter](https://stonecutter.kikugie.dev/).

- `./gradlew build` builds all versions. Jars land in `versions/<version>/build/libs/`.
- `./gradlew :1.20.1-forge:build` (or `:1.21.1-neoforge:...`) builds, runs (`runClient`) or datagens (`runData`) a single version.
- `src/` is written for the *active* version (1.21.1-neoforge when committed). Code that differs per version is wrapped in
  `//? if ...` comments, e.g. `//? if forge {` or `//? if >=1.21 {`. To edit the 1.20.1 side with full IDE support, run the
  `Set active project to 1.20.1-forge` task (or use the Stonecutter IntelliJ plugin), then `Reset active project` before committing.
- Per-version settings (Minecraft, loader and dependency versions) live in `versions/<version>/gradle.properties`;
  generated data lives in `versions/<version>/src/generated/resources`.

## Issues and suggestions

Found a bug, or have an idea for another dish or ingredient? Open an issue on the repository.