package archives.tater.dogwhistle.datagen;

import archives.tater.dogwhistle.DogWhistle;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModelGenerator extends FabricModelProvider {
    private static final TextureKey WHISTLE_TEXTURE = TextureKey.of("whistle");
    private static final TextureKey WHISTLE_OVERLAY_TEXTURE = TextureKey.of("whistle_overlay");
    private static final Model TEMPLATE_WHISTLE = new Model(Optional.of(new Identifier(DogWhistle.MOD_ID, "item/template_dog_whistle")), Optional.empty(), WHISTLE_TEXTURE, WHISTLE_OVERLAY_TEXTURE);

    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        TEMPLATE_WHISTLE.upload(ModelIds.getItemModelId(DogWhistle.DOG_WHISTLE), new TextureMap().put(WHISTLE_TEXTURE, TextureMap.getId(DogWhistle.DOG_WHISTLE)).put(WHISTLE_OVERLAY_TEXTURE, new Identifier(DogWhistle.MOD_ID, "item/blank")), itemModelGenerator.writer);
        DogWhistle.DYED_DOG_WHISTLES.values().forEach(item ->
                TEMPLATE_WHISTLE.upload(ModelIds.getItemModelId(item), new TextureMap().put(WHISTLE_TEXTURE, TextureMap.getId(DogWhistle.DOG_WHISTLE)).put(WHISTLE_OVERLAY_TEXTURE, new Identifier(DogWhistle.MOD_ID, TextureMap.getId(item).getPath() + "_overlay")), itemModelGenerator.writer)
        );
    }
}
