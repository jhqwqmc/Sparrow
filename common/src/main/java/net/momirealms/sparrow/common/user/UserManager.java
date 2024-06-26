package net.momirealms.sparrow.common.user;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface UserManager<T extends User<?>> {

    @NotNull T getUser(UUID uniqueId);
}
