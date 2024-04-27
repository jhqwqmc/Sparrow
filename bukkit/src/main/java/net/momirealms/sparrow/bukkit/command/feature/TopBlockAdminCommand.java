package net.momirealms.sparrow.bukkit.command.feature;

import net.kyori.adventure.text.Component;
import net.momirealms.sparrow.bukkit.SparrowBukkitPlugin;
import net.momirealms.sparrow.bukkit.command.AbstractCommand;
import net.momirealms.sparrow.bukkit.util.EntityUtils;
import net.momirealms.sparrow.common.locale.MessageConstants;
import net.momirealms.sparrow.common.locale.TranslationManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.incendo.cloud.Command;
import org.incendo.cloud.bukkit.BukkitCommandManager;
import org.incendo.cloud.bukkit.data.MultipleEntitySelector;
import org.incendo.cloud.bukkit.parser.selector.MultipleEntitySelectorParser;

public class TopBlockAdminCommand extends AbstractCommand {

    @Override
    public String getFeatureID() {
        return "topblock_admin";
    }

    @Override
    public Command.Builder<? extends CommandSender> assembleCommand(BukkitCommandManager<CommandSender> manager, Command.Builder<CommandSender> builder) {
        return builder
                .required("entity", MultipleEntitySelectorParser.multipleEntitySelectorParser(false))
                .flag(manager.flagBuilder("silent").withAliases("s"))
                .handler(commandContext -> {
                    MultipleEntitySelector selector = commandContext.get("entity");
                    boolean silent = commandContext.flags().hasFlag("silent");
                    var entities = selector.values();
                    for (Entity entity : entities) {
                        EntityUtils.toTopBlockPosition(entity);
                    }
                    if (!silent) {
                        if (entities.size() == 1) {
                            SparrowBukkitPlugin.getInstance().getSenderFactory()
                                    .wrap(commandContext.sender())
                                    .sendMessage(
                                            TranslationManager.render(
                                                    MessageConstants.COMMANDS_ADMIN_TOP_BLOCK_SUCCESS_SINGLE
                                                            .arguments(Component.text(entities.iterator().next().getName()))
                                                            .build()
                                            ),
                                            true
                                    );
                        } else {
                            SparrowBukkitPlugin.getInstance().getSenderFactory()
                                    .wrap(commandContext.sender())
                                    .sendMessage(
                                            TranslationManager.render(
                                                    MessageConstants.COMMANDS_ADMIN_TOP_BLOCK_SUCCESS_MULTIPLE
                                                            .arguments(Component.text(entities.size()))
                                                            .build()
                                            ),
                                            true
                                    );
                        }
                    }
                });
    }
}
