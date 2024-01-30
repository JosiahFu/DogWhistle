package archives.tater.dogwhistle.datagen;

import archives.tater.dogwhistle.DogWhistle;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.util.DyeColor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class EnglishLangGenerator extends FabricLanguageProvider {
    protected EnglishLangGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(DogWhistle.DOG_WHISTLE, "Dog Whistle");
        translationBuilder.add(DogWhistle.DOG_WHISTLE.getTranslationKey() + ".sit", "All dogs were sat");
        translationBuilder.add(DogWhistle.DOG_WHISTLE.getTranslationKey() + ".summon", "All dogs were summoned");
        translationBuilder.add(DogWhistle.DOG_WHISTLE.getTranslationKey() + ".nonefound", "No dogs were found in the range");


        DogWhistle.DYED_DOG_WHISTLES.forEach(((dyeColor, item) -> translationBuilder.add(item, COLOR_NAMES.get(dyeColor) + " Dog Whistle")));
    }

    private static final Map<DyeColor, String> COLOR_NAMES = Map.ofEntries(
            entry(DyeColor.PINK, "Pink"),
            entry(DyeColor.BLACK, "Black"),
            entry(DyeColor.BLUE, "Blue"),
            entry(DyeColor.BROWN, "Brown"),
            entry(DyeColor.CYAN, "Cyan"),
            entry(DyeColor.GRAY, "Gray"),
            entry(DyeColor.GREEN, "Green"),
            entry(DyeColor.LIGHT_BLUE, "Light Blue"),
            entry(DyeColor.LIGHT_GRAY, "Light Gray"),
            entry(DyeColor.LIME, "Lime"),
            entry(DyeColor.MAGENTA, "Magenta"),
            entry(DyeColor.ORANGE, "Orange"),
            entry(DyeColor.PURPLE, "Purple"),
            entry(DyeColor.RED, "Red"),
            entry(DyeColor.WHITE, "White"),
            entry(DyeColor.YELLOW, "Yellow")
    );
}
