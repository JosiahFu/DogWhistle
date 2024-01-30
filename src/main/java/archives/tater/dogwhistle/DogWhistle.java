package archives.tater.dogwhistle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DogWhistle implements ModInitializer {
    public static final String MOD_ID = "dogwhistle";
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Item DOG_WHISTLE = Registry.register(
            Registries.ITEM,
            new Identifier(MOD_ID, "dog_whistle"),
            new DogWhistleItem(new FabricItemSettings().maxCount(1))
    );

    public static final Map<DyeColor, Item> DYED_DOG_WHISTLES = Arrays.stream(DyeColor.values()).collect(Collectors.toMap(
            dyeColor -> dyeColor,
            dyeColor -> Registry.register(
                    Registries.ITEM,
                    new Identifier(MOD_ID, dyeColor.getName() + "_dog_whistle"),
                    new DyedDogWhistleItem(dyeColor, new FabricItemSettings().maxCount(1))
            )
    ));

    public static final ItemGroup DOG_WHISTLE_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(MOD_ID, "dog_whistles"),
            FabricItemGroup.builder()
                .icon(DOG_WHISTLE::getDefaultStack)
                .displayName(Text.translatable("itemGroup.dogwhistle.dog_whistles"))
                .entries((context, entries) -> {
                    entries.add(DOG_WHISTLE);
                    DYED_DOG_WHISTLES.values().forEach(entries::add);
                })
                .build()
    );

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

    }
}
