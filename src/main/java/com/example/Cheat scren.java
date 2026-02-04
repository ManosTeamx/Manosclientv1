package net.fabricmc.example;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class CheatScreen extends Screen {
    public CheatScreen() { super(Text.of("Manos Cheat")); }

    @Override
    protected void init() {
        int y = this.height / 2 - 90;

        // Manos Cheat Buton Listesi
        addBtn("Legit Totem", ExampleMod.autoTotem, y, b -> ExampleMod.autoTotem = !ExampleMod.autoTotem);
        addBtn("Inv Totem (Manos)", ExampleMod.invTotem, y + 25, b -> ExampleMod.invTotem = !ExampleMod.invTotem);
        addBtn("Crystal Macro", ExampleMod.crystalMacro, y + 50, b -> ExampleMod.crystalMacro = !ExampleMod.crystalMacro);
        addBtn("Anchor Macro", ExampleMod.anchorMacro, y + 75, b -> ExampleMod.anchorMacro = !ExampleMod.anchorMacro);
        
        this.addDrawableChild(ButtonWidget.builder(Text.of("Manos Reach: " + ExampleMod.reachDistance), b -> {
            ExampleMod.reachDistance = (ExampleMod.reachDistance >= 4.0) ? 3.0 : ExampleMod.reachDistance + 0.2;
            b.setMessage(Text.of("Manos Reach: " + String.format("%.1f", ExampleMod.reachDistance)));
        }).dimensions(this.width / 2 - 100, y + 100, 200, 20).build());
    }

    private void addBtn(String name, boolean state, int y, ButtonWidget.PressAction action) {
        this.addDrawableChild(ButtonWidget.builder(Text.of(name + ": " + (state ? "ON" : "OFF")), b -> {
            action.onPress(b);
            b.setMessage(Text.of(name + ": " + (name.contains("ON") ? "OFF" : "ON"))); // Basit görsel güncelleme
        }).dimensions(this.width / 2 - 100, y, 200, 20).build());
    }
                                                   }
