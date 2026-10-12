
package com.gyc.ancientmyth.client.ui;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.Optional;

public final class MythGradient {

    // 紫、青、金、绯粉、粉白
    private static final int[] PALETTE = {
            0xAC90F5,
            0x63DDD1,
            0xF2CB8D,
            0xE995B2,
            0xF6E8FF
    };

    // 10 秒循环一次
    private static final long PERIOD_NANOS =
            10_000_000_000L;

    // 相邻字符的色带偏移量
    private static final double CHARACTER_OFFSET = 0.35;

    private MythGradient() {
    }

    public static Component animate(Component original) {
        MutableComponent result = Component.empty();

        double time = (double) Math.floorMod(
                System.nanoTime(),
                PERIOD_NANOS
        ) / PERIOD_NANOS;

        double phase = time * PALETTE.length;

        int[] index = {0};

        // 遍历已经翻译和展开的文本，保留其原有 Style
        original.visit((style, segment) -> {

            segment.codePoints().forEach(codePoint -> {
                double position =
                        phase + index[0] * CHARACTER_OFFSET;

                int color = interpolate(position);

                result.append(
                        Component.literal(
                                new String(Character.toChars(codePoint))
                        ).setStyle(style.withColor(color))
                );

                index[0]++;
            });

            return Optional.empty();

        }, Style.EMPTY);

        return result;
    }

    private static int interpolate(double position) {
        int base = (int) Math.floor(position);

        int first = Math.floorMod(base, PALETTE.length);
        int second = (first + 1) % PALETTE.length;

        double t = position - Math.floor(position);

        // Smoothstep，使颜色过渡更平滑
        t = t * t * (3.0 - 2.0 * t);

        int a = PALETTE[first];
        int b = PALETTE[second];

        int red = blend(
                (a >> 16) & 255,
                (b >> 16) & 255,
                t
        );

        int green = blend(
                (a >> 8) & 255,
                (b >> 8) & 255,
                t
        );

        int blue = blend(
                a & 255,
                b & 255,
                t
        );

        return (red << 16) | (green << 8) | blue;
    }

    private static int blend(int a, int b, double t) {
        return (int) Math.round(a + (b - a) * t);
    }
}
