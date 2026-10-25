pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
        maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
    }
}

plugins {
    // https://stonecutter.kikugie.dev/
    id("dev.kikugie.stonecutter") version "0.9.8"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

stonecutter {
    create(rootProject) {
        /**
         * Registers `versions/{minecraft}-{loader}` for each loader, using `build.{loader}.gradle.kts`.
         * The Minecraft part is what `//? if >=1.21` style comments compare against.
         */
        fun match(minecraft: String, vararg loaders: String) {
            for (loader in loaders) version("$minecraft-$loader", minecraft).buildscript("build.$loader.gradle.kts")
        }

        match("1.20.1", "forge")
        match("1.21.1", "neoforge")

        // The version the code in src/ is written for when committed.
        vcsVersion = "1.21.1-neoforge"
    }
}

rootProject.name = "Farmers-O-Plenty"
