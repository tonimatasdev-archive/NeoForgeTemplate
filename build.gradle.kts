plugins {
    `java-library`
    eclipse
    idea
    id("net.neoforged.moddev") version "1.+"
}

val modVersion: String by extra
val neoVersion: String by extra
val neoVersionRange: String by extra
val minecraftVersion: String by extra
val loaderVersionRange: String by extra

version = modVersion
group = "dev.tonimatas"

repositories {
    mavenLocal()
}

base.archivesName.set("ethylene")

java.toolchain.languageVersion = JavaLanguageVersion.of(21)

neoForge {
    version = neoVersion

    accessTransformers.from("src/main/resources/META-INF/accesstransformer.cfg")

    runs {
        create("client") {
            client()

            systemProperty("neoforge.enabledGameTestNamespaces", "ethylene")
        }

        create("server") {
            server()
            programArgument("--nogui")
            systemProperty("neoforge.enabledGameTestNamespaces", "ethylene")
        }

        create("gameTestServer") {
            type = "gameTestServer"
            systemProperty ("neoforge.enabledGameTestNamespaces", "ethylene")
        }

        create("data") {
            data()
            
            programArguments.addAll("--mod", "ethylene", "--all", "--output", file("src/generated/resources/").absolutePath, "--existing", file("src/main/resources/").absolutePath)
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel = org.slf4j.event.Level.DEBUG
        }
    }

    mods {
        create("ethylene") {
            sourceSet(sourceSets.main.get())
        }
    }
}

sourceSets.main.get().resources.srcDir("src/generated/resources")


dependencies {
    
}

tasks.withType<ProcessResources> {
    val replaceProperties = mapOf("minecraftVersion" to minecraftVersion, "modVersion" to modVersion, 
        "neoVersionRange" to neoVersionRange, "loaderVersionRange" to loaderVersionRange)
    
    inputs.properties(replaceProperties)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(replaceProperties)
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}
