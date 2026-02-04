package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.screen.slot.SlotActionType;
import java.util.Random;

public class ExampleMod implements ModInitializer {
    // Manos Cheat Kontrol Paneli
    public static boolean autoTotem = false;
    public static boolean invTotem = false;
    public static boolean crystalMacro = false;
    public static boolean anchorMacro = false;
    public static double reachDistance = 3.0;

    private static final Random rnd = new Random();
    private static long lastAction = 0;

    @Override
    public void onInitialize() {
        System.out.println("Manos Cheat v4.0 - Premium Edition Loaded!");
    }

    public static void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;
        long now = System.currentTimeMillis();

        if (invTotem && mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
            if (now - lastAction > (70 + rnd.nextInt(50))) {
                int slot = findTotemSlot(mc.player.getInventory());
                if (slot != -1) {
                    mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, slot, 40, SlotActionType.SWAP, mc.player);
                    lastAction = now;
                }
            }
        }

        if (crystalMacro && now - lastAction > (60 + rnd.nextInt(40))) {
            mc.world.getEntities().forEach(e -> {
                if (e instanceof EndCrystalEntity && mc.player.distanceTo(e) <= reachDistance) {
                    mc.interactionManager.attackEntity(mc.player, e);
                    mc.player.swingHand(Hand.MAIN_HAND);
                    lastAction = now;
                }
            });
        }
    }

    private static int findTotemSlot(PlayerInventory inv) {
        for (int i = 0; i < 36; i++) {
            if (inv.getStack(i).getItem() == Items.TOTEM_OF_UNDYING) return i;
        }
        return -1;
    }
}
