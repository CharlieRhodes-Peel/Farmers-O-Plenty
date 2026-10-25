import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters

// Decompiling/recompiling Minecraft is heavy. With org.gradle.parallel=true every version would do it at
// once, so this makes the `createMinecraftArtifacts` tasks of all versions take turns.
interface MinecraftArtifactsMutex : BuildService<BuildServiceParameters.None>

val mutex = gradle.sharedServices.registerIfAbsent("createMinecraftArtifactsMutex", MinecraftArtifactsMutex::class.java) {
    maxParallelUsages.set(1)
}

tasks.named { it == "createMinecraftArtifacts" }.configureEach {
    usesService(mutex)
}
