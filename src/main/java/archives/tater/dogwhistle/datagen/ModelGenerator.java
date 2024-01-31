package archives.tater.dogwhistle.datagen;

import archives.tater.dogwhistle.DogWhistle;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

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
        DogWhistle.DYED_DOG_WHISTLES.values().forEach(item ->
                Models.GENERATED_TWO_LAYERS.upload(ModelIds.getItemModelId(item), TextureMap.layered(TextureMap.getId(DogWhistle.DOG_WHISTLE), new Identifier(DogWhistle.MOD_ID, TextureMap.getId(item).getPath() + "_overlay")), itemModelGenerator.writer)
        );
    }
}
