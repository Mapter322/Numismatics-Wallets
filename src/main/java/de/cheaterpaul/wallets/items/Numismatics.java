package de.cheaterpaul.wallets.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Numismatics {

    public enum Tier {
        SPUR,
        BEVEL,
        SPROCKET,
        COG,
        CROWN,
        SUN
    }

    private static final Map<Tier, Item> TIER_TO_ITEM = new EnumMap<>(Tier.class);
    private static final Map<Tier, Integer> TIER_TO_UNIT = new EnumMap<>(Tier.class);
    private static final Map<Item, Integer> ITEM_TO_VALUE = new HashMap<>();
    private static final List<Tier> ORDERED_DESC;

    static {
        // Resolve items from numismatics mod
        TIER_TO_ITEM.put(Tier.SPUR, ForgeRegistries.ITEMS.getValue(new ResourceLocation("numismatics","spur")));
        TIER_TO_ITEM.put(Tier.BEVEL, ForgeRegistries.ITEMS.getValue(new ResourceLocation("numismatics","bevel")));
        TIER_TO_ITEM.put(Tier.SPROCKET, ForgeRegistries.ITEMS.getValue(new ResourceLocation("numismatics","sprocket")));
        TIER_TO_ITEM.put(Tier.COG, ForgeRegistries.ITEMS.getValue(new ResourceLocation("numismatics","cog")));
        TIER_TO_ITEM.put(Tier.CROWN, ForgeRegistries.ITEMS.getValue(new ResourceLocation("numismatics","crown")));
        TIER_TO_ITEM.put(Tier.SUN, ForgeRegistries.ITEMS.getValue(new ResourceLocation("numismatics","sun")));

        // Unit values
        TIER_TO_UNIT.put(Tier.SPUR, 1);
        TIER_TO_UNIT.put(Tier.BEVEL, 8);
        TIER_TO_UNIT.put(Tier.SPROCKET, 16);
        TIER_TO_UNIT.put(Tier.COG, 64);
        TIER_TO_UNIT.put(Tier.CROWN, 512);
        TIER_TO_UNIT.put(Tier.SUN, 4096);

        // Reverse map item -> unit value
        ITEM_TO_VALUE.put(TIER_TO_ITEM.get(Tier.SPUR), 1);
        ITEM_TO_VALUE.put(TIER_TO_ITEM.get(Tier.BEVEL), 8);
        ITEM_TO_VALUE.put(TIER_TO_ITEM.get(Tier.SPROCKET), 16);
        ITEM_TO_VALUE.put(TIER_TO_ITEM.get(Tier.COG), 64);
        ITEM_TO_VALUE.put(TIER_TO_ITEM.get(Tier.CROWN), 512);
        ITEM_TO_VALUE.put(TIER_TO_ITEM.get(Tier.SUN), 4096);

        ORDERED_DESC = List.of(
                Tier.SUN,
                Tier.CROWN,
                Tier.COG,
                Tier.SPROCKET,
                Tier.BEVEL,
                Tier.SPUR
        );
    }

    private Numismatics() {}

    public static boolean isSupported(Item item) {
        return ITEM_TO_VALUE.containsKey(item);
    }

    public static int getUnitValue(Item item) {
        return ITEM_TO_VALUE.getOrDefault(item, 0);
    }

    public static Item getItemFor(Tier value) {
        return TIER_TO_ITEM.get(value);
    }

    public static int getUnit(Tier value) {
        return TIER_TO_UNIT.getOrDefault(value, 0);
    }

    public static List<Tier> orderedByValueDesc() {
        return ORDERED_DESC;
    }
}
