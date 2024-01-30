package archives.tater.dogwhistle.datagen;

import archives.tater.dogwhistle.DogWhistle;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TagGenerator extends FabricTagProvider.ItemTagProvider {

    public TagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        FabricTagProvider<Item>.FabricTagBuilder dogWhistles = getOrCreateTagBuilder(DogWhistle.DOG_WHISTLES_TAG);
        dogWhistles.add(DogWhistle.DOG_WHISTLE);
        DogWhistle.DYED_DOG_WHISTLES.values().forEach(dogWhistles::add);

        FabricTagProvider<Item>.FabricTagBuilder dyedDogWhistles = getOrCreateTagBuilder(DogWhistle.DYED_DOG_WHISTLES_TAG);
        DogWhistle.DYED_DOG_WHISTLES.values().forEach(dyedDogWhistles::add);

    }
}
