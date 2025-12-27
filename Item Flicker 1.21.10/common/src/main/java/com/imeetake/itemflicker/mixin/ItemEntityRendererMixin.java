package com.imeetake.itemflicker.mixin;

import com.imeetake.itemflicker.config.ItemFlickerConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.state.ItemEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntityRenderer.class)
public class ItemEntityRendererMixin {

    @Inject(method = "submit(Lnet/minecraft/client/renderer/entity/state/ItemEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
            at = @At("HEAD"),
            cancellable = true)
    private void itemflicker$onSubmit(ItemEntityRenderState renderState, PoseStack poseStack,
                                      SubmitNodeCollector collector, CameraRenderState cameraState,
                                      CallbackInfo ci) {
        if (shouldSkipRender(renderState)) {
            ci.cancel();
        }
    }

    private boolean shouldSkipRender(ItemEntityRenderState renderState) {
        ItemFlickerConfig config = ItemFlickerConfig.get();
        if (config == null) return false;

        float ageInTicks = renderState.ageInTicks;
        if (ageInTicks < 0) return false;

        int lifespan = 6000;
        float remaining = lifespan - ageInTicks;
        int threshold = config.getThresholdTicks();

        if (remaining > threshold || remaining <= 0) {
            return false;
        }

        float progress = 1.0f - remaining / threshold;
        float smoothProgress = (float) Math.sqrt(progress);
        float baseFrequency = 1.5f + smoothProgress * 6.35f;
        float frequency = baseFrequency * config.getSpeedMultiplier();

        float time = ageInTicks * frequency * 0.075f / 1.25f;
        float wave = (float) Math.sin(time);

        return wave < 0;
    }
}