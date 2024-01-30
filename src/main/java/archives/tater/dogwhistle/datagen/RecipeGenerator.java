package archives.tater.dogwhistle.datagen;

import archives.tater.dogwhistle.DogWhistle;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.TameAnimalCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.DyeColor;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class RecipeGenerator extends FabricRecipeProvider {
    public RecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, DogWhistle.DOG_WHISTLE)
                .pattern("  #")
                .pattern(" # ")
                .pattern("%  ")
                .input('#', Items.BAMBOO)
                .input('%', Items.BONE)
                .criterion("tamed_wolf", TameAnimalCriterion.Conditions.create(
                        EntityPredicate.Builder.create().type(EntityType.WOLF).build())
                )
                .offerTo(exporter);

        DogWhistle.DYED_DOG_WHISTLES.forEach((dyeColor, item) -> ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, item)
                .input(DYE_ITEMS.get(dyeColor))
                .input(Ingredient.ofStacks(DOG_WHISTLE_ITEMS.stream().filter(dwItem -> dwItem != item).map(Item::getDefaultStack)))
                .group("dye_dog_whistle")
                .criterion("has_needed_dye", conditionsFromItem(DYE_ITEMS.get(dyeColor)))
                .offerTo(exporter, DogWhistle.MOD_ID + ":dye_" + getItemPath(item))
        );

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, DogWhistle.DOG_WHISTLE)
                .input(DogWhistle.DYED_DOG_WHISTLES_TAG)
                .criterion("has_dyed_dog_whistle", conditionsFromTag(DogWhistle.DYED_DOG_WHISTLES_TAG))
                .offerTo(exporter, DogWhistle.MOD_ID + ":undye_dog_whistle");
    }

    private static final Map<DyeColor, Item> DYE_ITEMS = Map.ofEntries(
            entry(DyeColor.PINK, Items.PINK_DYE),
            entry(DyeColor.BLACK, Items.BLACK_DYE),
            entry(DyeColor.BLUE, Items.BLUE_DYE),
            entry(DyeColor.BROWN, Items.BROWN_DYE),
            entry(DyeColor.CYAN, Items.CYAN_DYE),
            entry(DyeColor.GRAY, Items.GRAY_DYE),
            entry(DyeColor.GREEN, Items.GREEN_DYE),
            entry(DyeColor.LIGHT_BLUE, Items.LIGHT_BLUE_DYE),
            entry(DyeColor.LIGHT_GRAY, Items.LIGHT_GRAY_DYE),
            entry(DyeColor.LIME, Items.LIME_DYE),
            entry(DyeColor.MAGENTA, Items.MAGENTA_DYE),
            entry(DyeColor.ORANGE, Items.ORANGE_DYE),
            entry(DyeColor.PURPLE, Items.PURPLE_DYE),
            entry(DyeColor.RED, Items.RED_DYE),
            entry(DyeColor.WHITE, Items.WHITE_DYE),
            entry(DyeColor.YELLOW, Items.YELLOW_DYE)
    );

    private static final List<Item> DOG_WHISTLE_ITEMS = Stream.concat(DogWhistle.DYED_DOG_WHISTLES.values().stream(), Stream.of(DogWhistle.DOG_WHISTLE)).toList();
}
