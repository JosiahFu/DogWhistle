package archives.tater.dogwhistle;

import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.DyeColor;

public class DyedDogWhistleItem extends DogWhistleItem {
    public final DyeColor color;

    public DyedDogWhistleItem(DyeColor color, Settings settings) {
        super(settings);
        this.color = color;
    }

    @Override
    protected boolean isWolfAffected(WolfEntity wolf) {
        return wolf.getCollarColor() == color;
    }
}
