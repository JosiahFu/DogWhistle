package archives.tater.dogwhistle;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class DogWhistleItem extends Item {
    public DogWhistleItem(Settings settings) {
        super(settings);
    }

    private static final int duration = 40;

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (world.isClient) return TypedActionResult.success(itemStack, false);

        int range = getRange(itemStack);
        List<WolfEntity> wolves = world.getEntitiesByType(EntityType.WOLF, new Box(user.getBlockPos().add(-range, -range, -range), user.getBlockPos().add(range, range, range)), entity -> entity.isOwner(user) && isWolfAffected(entity));
        if (wolves.isEmpty()) {
            user.sendMessage(Text.translatable("item.dogwhistle.dog_whistle.nonefound"), true);
        } else if (user.isSneaking()) {
            wolves.forEach(wolf -> wolf.setSitting(true));
            user.sendMessage(Text.translatable("item.dogwhistle.dog_whistle.sit"), true);
        } else {
            wolves.forEach(wolf -> {
                wolf.setSitting(false);
                wolf.getNavigation().startMovingTo(user, 1.0);
            });
            user.sendMessage(Text.translatable("item.dogwhistle.dog_whistle.summon"), true);
        }

        playSound(world, user);
        user.getItemCooldownManager().set(this, duration);
        user.setCurrentHand(hand);

        return TypedActionResult.success(itemStack, false);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.TOOT_HORN;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return duration;
    }

    // Enchantments in the future?
    protected int getRange(ItemStack itemStack) {
        return 64;
    }

    protected boolean isWolfAffected(WolfEntity wolf) {
        return true;
    }

    private static void playSound(World world, PlayerEntity player) {
        world.playSoundFromEntity(player, player, DogWhistle.DOG_WHISTLE_SOUND, SoundCategory.PLAYERS, 1.0f, 1.0F);
        world.emitGameEvent(GameEvent.INSTRUMENT_PLAY, player.getPos(), GameEvent.Emitter.of(player));
    }

}
