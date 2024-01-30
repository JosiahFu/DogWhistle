package archives.tater.dogwhistle;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class DogWhistleItem extends Item {
    public DogWhistleItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (world.isClient) return TypedActionResult.success(itemStack);

        int range = getRange();
        List<WolfEntity> wolves = world.getEntitiesByType(EntityType.WOLF, new Box(user.getBlockPos().add(-range, -range, -range), user.getBlockPos().add(range, range, range)), entity -> entity.isOwner(user) && isWolfAffected(entity));
        if (wolves.isEmpty()) {
            user.sendMessage(Text.translatable("item.dogwhistle.dog_whistle.nonefound"), true);
        } else if (wolves.stream().noneMatch(TameableEntity::isSitting)) {
            wolves.forEach(wolf -> wolf.setSitting(true));
            user.sendMessage(Text.translatable("item.dogwhistle.dog_whistle.sit"), true);
        } else {
            wolves.forEach(wolf -> {
                wolf.setSitting(false);
                wolf.getNavigation().startMovingTo(user, 1.0);
            });
            user.sendMessage(Text.translatable("item.dogwhistle.dog_whistle.summon"), true);
        }

        return TypedActionResult.success(itemStack);
    }

    protected int getRange() {
        return 64;
    }

    protected boolean isWolfAffected(WolfEntity wolf) {
        return true;
    }
}
