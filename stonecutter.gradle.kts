plugins {
    id("dev.kikugie.stonecutter")
    // Declared once here so both loader buildscripts share the same plugin classes.
    id("net.neoforged.moddev") version "2.0.148" apply false
    id("net.neoforged.moddev.legacyforge") version "2.0.148" apply false
}

tasks.named<Wrapper>("wrapper") {
    // Define wrapper values here so as to not have to always do so when updating gradlew.properties.
    // Switching this to Wrapper.DistributionType.ALL will download the full gradle sources that comes with
    // documentation attached on cursor hover of gradle classes and methods. However, this comes with increased
    // file size for Gradle. If you do switch this to ALL, run the Gradle wrapper task twice afterwards.
    // (Verify by checking gradle/wrapper/gradle-wrapper.properties to see if distributionUrl now points to `-all`)
    distributionType = Wrapper.DistributionType.BIN
}

// The version src/ is currently switched to. Change it with the Stonecutter IntelliJ plugin
// or the "Set active project to ..." Gradle tasks, not by hand.
stonecutter active "1.21.1-neoforge"

// See https://stonecutter.kikugie.dev/wiki/v2/reference/syntax/conditions
stonecutter parameters {
    val loader = current.project.substringAfterLast('-')

    // Lets the code use `//? if forge {` / `//? if neoforge {`.
    constants.match(loader, "forge", "neoforge")

    // Plain renames Mojang made between 1.20.1 and 1.21.1. Source is written the 1.21.1 way and
    // these are flipped automatically when building (or switching to) an older version.
    // Only add one-to-one renames here; anything else belongs in a //? if comment.
    replacements.string(current.parsed < "1.21") {
        replace("ResourceLocation.fromNamespaceAndPath(", "new ResourceLocation(")
        replace("BlockBehaviour.Properties.ofFullCopy(", "BlockBehaviour.Properties.copy(")
        replace(".saturationModifier(", ".saturationMod(")
        replace(".alwaysEdible()", ".alwaysEat()")
    }
}
