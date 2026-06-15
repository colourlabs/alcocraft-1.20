package net.hadrus.alcocraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.hadrus.alcocraft.AlcoCraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;

public class KegScreen extends AbstractContainerScreen<KegMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AlcoCraft.MOD_ID, "textures/gui/keg_gui.png");
    private static final ResourceLocation BG =
            new ResourceLocation(AlcoCraft.MOD_ID, "textures/gui/dark_bg.png");

    private static final Fluid fluid = Fluids.WATER;
    private static final int[] BUBBLELENGTHS = new int[]{28, 24, 20, 16, 11, 6, 0};

    public KegScreen(KegMenu kegMenu, Inventory inventory, Component component) {
        super(kegMenu, inventory, component);
    }

    public static int rawColorFromRGB(int red, int green, int blue) {
        int rgb = Math.max(Math.min(0xFF, red), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, green), 0);
        rgb = (rgb << 8) + Math.max(Math.min(0xFF, blue), 0);
        return rgb;
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int pMouseX, int pMouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, rawColorFromRGB(215, 171, 121), false);
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);

        if (!menu.isCrafting()) {
            guiGraphics.drawString(this.font, "00:00", 144, 50, rawColorFromRGB(97, 69, 36), false);
            guiGraphics.drawString(this.font, "00:00", 143, 49, rawColorFromRGB(215, 171, 121), false);
        } else {
            int time = this.menu.getProgress() / 20;
            int minutes = time / 60;
            int seconds = time % 60;

            String min = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
            String sec = seconds < 10 ? "0" + seconds : String.valueOf(seconds);

            guiGraphics.drawString(this.font, min + ":" + sec, 144, 50, rawColorFromRGB(97, 69, 36), false);
            guiGraphics.drawString(this.font, min + ":" + sec, 143, 49, rawColorFromRGB(215, 171, 121), false);
        }
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // Background
        int x = (width - imageWidth) / 2 + 3;
        int y = (height - imageHeight) / 2 + 3;
        guiGraphics.blit(BG, x, y, 0, 0, imageWidth - 6, imageHeight - 6);

        // Input fluid (water)
        IClientFluidTypeExtensions fluidExt = IClientFluidTypeExtensions.of(fluid);
        ResourceLocation location = fluidExt.getStillTexture();
        TextureAtlasSprite sprite = minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location);
        int color = fluidExt.getTintColor();

        int z = this.menu.getWaterlevel();
        x = (width - sprite.contents().width()) / 2 - 80;
        y = (height - sprite.contents().height()) / 2 - 19 - z;

        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        RenderSystem.setShaderColor(
                ((color >> 16) & 0xFF) / 255f,
                ((color >> 8) & 0xFF) / 255f,
                (color & 0xFF) / 255f,
                ((color >> 24) & 0xFF) / 255f);

        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 8; i++) {
                guiGraphics.blit(x + 16 * i, y + 16 * j, 0, 16, 16, sprite);
            }
        }

        // Result fluid (beer)
        location = fluidExt.getStillTexture();
        sprite = minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location);

        z = this.menu.getBeerLevel();
        x = (width - sprite.contents().width()) / 2 - 80;
        y = (height - sprite.contents().height()) / 2 - 19 - z;

        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);

        switch (menu.getBeerType()) {
            case 1  -> RenderSystem.setShaderColor(222/255f, 167/255f,  81/255f, 1f);
            case 2  -> RenderSystem.setShaderColor(201/255f, 125/255f,  31/255f, 1f);
            case 3  -> RenderSystem.setShaderColor(107/255f,  30/255f,   5/255f, 1f);
            case 4  -> RenderSystem.setShaderColor( 29/255f,   5/255f,   3/255f, 1f);
            case 5  -> RenderSystem.setShaderColor(170/255f,  14/255f,   1/255f, 1f);
            case 6  -> RenderSystem.setShaderColor( 75/255f, 165/255f, 128/255f, 1f);
            case 7  -> RenderSystem.setShaderColor( 58/255f,  70/255f, 123/255f, 1f);
            case 8  -> RenderSystem.setShaderColor(143/255f, 181/255f, 246/255f, 1f);
            case 9  -> RenderSystem.setShaderColor(188/255f, 137/255f,  39/255f, 1f);
            case 10 -> RenderSystem.setShaderColor( 69/255f, 201/255f,  72/255f, 1f);
            case 11 -> RenderSystem.setShaderColor(142/255f, 102/255f, 141/255f, 1f);
            case 12 -> RenderSystem.setShaderColor(199/255f, 183/255f, 255/255f, 1f);
        }

        for (int j = 0; j <= 3; j++) {
            for (int i = 0; i <= 8; i++) {
                guiGraphics.blit(x + 16 * i, y + 16 * j, 0, 16, 16, sprite);
            }
        }

        // Reset color before drawing GUI texture
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        x = (width - imageWidth) / 2;
        y = (height - imageHeight) / 2;
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight + 2);

        if (this.menu.isCrafting()) {
            int i1 = this.menu.getProgress();
            int j1 = BUBBLELENGTHS[i1 / 2 % 7];
            guiGraphics.blit(TEXTURE, x + 150, y + 17 + 28 - j1, 176, 28 - j1, 11, j1);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}