package archives.tater.dogwhistle.datagen;

import archives.tater.dogwhistle.DogWhistle;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(DogWhistle.DOG_WHISTLE, Models.GENERATED);
        DogWhistle.DYED_DOG_WHISTLES.values().forEach(item -> itemModelGenerator.register(item, Models.GENERATED));
    }
}
