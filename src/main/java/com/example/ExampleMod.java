package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.screen.slot.SlotActionType;
import java.util.Random;

public class ExampleMod implements ModInitializer {
    public static boolean autoTotem = false;
    public static boolean invTotem = false;
    public static boolean crystalMacro = false;
    public static boolean anchorMacro = false;
    public static double reachDistance = 3.0;

    private static final Random rnd = new Random();
    private static long lastAction = 0;

    @Override
    public void onInitialize() {
        System.out.println("Manos Cheat Loaded!");
    }

    public static void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;
        long now = System.currentTimeMillis();

        if (invTotem && mc.player.getOffHandStack().getItem() != Items.TOTEM_OF_UNDYING) {
            if (now - lastAction > 100) {
                lastAction = now;
            }
        }
    }
}
