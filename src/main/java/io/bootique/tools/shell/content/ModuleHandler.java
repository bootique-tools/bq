package io.bootique.tools.shell.content;

import io.bootique.command.CommandOutcome;

public class ModuleHandler extends ContentHandler {
    @Override
    public CommandOutcome handle(String name) {
        return CommandOutcome.failed(-1, "Not yet implemented");
    }
}
