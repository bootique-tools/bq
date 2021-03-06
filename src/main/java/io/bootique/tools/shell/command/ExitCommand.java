package io.bootique.tools.shell.command;

import java.util.Collection;
import java.util.Collections;

import io.bootique.cli.Cli;
import io.bootique.command.CommandOutcome;
import io.bootique.command.CommandWithMetadata;
import io.bootique.meta.application.CommandMetadata;

public class ExitCommand extends CommandWithMetadata implements ShellCommand {

    public ExitCommand() {
        super(CommandMetadata.builder("exit")
                .description("Exit from shell")
                .build());
    }

    @Override
    public CommandOutcome run(Cli cli) {
        return CommandOutcome.failed(ShellCommand.TERMINATING_EXIT_CODE, "");
    }

    @Override
    public Collection<String> aliases() {
        return Collections.singleton("quit");
    }
}
