package io.bootique.tools.shell.content;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import io.bootique.command.CommandOutcome;
import io.bootique.tools.shell.Shell;
import io.bootique.tools.shell.template.TemplatePipeline;

public abstract class ContentHandler {

    @Inject
    private Shell shell;

    protected final List<TemplatePipeline> pipelines = new ArrayList<>();

    protected void addPipeline(TemplatePipeline.Builder builder) {
        pipelines.add(builder.build());
    }

    public abstract CommandOutcome handle(String name);
}