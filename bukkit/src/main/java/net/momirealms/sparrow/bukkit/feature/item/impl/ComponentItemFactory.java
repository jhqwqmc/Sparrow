package net.momirealms.sparrow.bukkit.feature.item.impl;

import com.saicone.rtag.RtagItem;
import com.saicone.rtag.data.ComponentType;
import net.momirealms.sparrow.bukkit.SparrowBukkitPlugin;
import net.momirealms.sparrow.bukkit.feature.item.SparrowBukkitItemFactory;
import net.momirealms.sparrow.common.feature.item.ComponentKeys;

import java.util.Optional;

@SuppressWarnings("UnstableApiUsage")
public class ComponentItemFactory extends SparrowBukkitItemFactory {

    public ComponentItemFactory(SparrowBukkitPlugin plugin) {
        super(plugin);
    }

    @Override
    protected void customModelData(RtagItem item, Integer data) {
        if (data == null) {
            item.removeComponent(ComponentKeys.CUSTOM_MODEL_DATA);
        } else {
            item.setComponent(ComponentKeys.CUSTOM_MODEL_DATA, data);
        }
    }

    @Override
    protected Optional<Integer> customModelData(RtagItem item) {
        if (!item.hasComponent(ComponentKeys.CUSTOM_MODEL_DATA)) return Optional.empty();
        return Optional.ofNullable(
                (Integer) ComponentType.encodeJava(
                        ComponentKeys.CUSTOM_MODEL_DATA,
                    item.getComponent(ComponentKeys.CUSTOM_MODEL_DATA)
                ).orElse(null)
        );
    }
}