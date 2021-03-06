package io.bootique.tools.shell.template.processor;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.bootique.tools.shell.template.Properties;
import io.bootique.tools.shell.template.Template;

public class JavaPackageProcessor implements TemplateProcessor {

    static final String TEMPLATE_PACKAGE = "example";

    @Override
    public Template process(Template template, Properties properties) {
        return template
                .withPath(outputPath(template, properties))
                .withContent(processContent(template, properties));
    }

    String processContent(Template template, Properties properties) {
        String content = template.getContent();
        content = replacePackageDeclaration(content, properties);
        content = replaceImportDeclaration(content, properties);
        return content;
    }

    String replacePackageDeclaration(String content, Properties properties) {
        return content.replaceAll("\\bpackage " + TEMPLATE_PACKAGE, "package " + properties.get("java.package"));
    }

    String replaceImportDeclaration(String content, Properties properties) {
        return content.replaceAll("\\bimport " + TEMPLATE_PACKAGE, "import " + properties.get("java.package"));
    }

    Path outputPath(Template template, Properties properties) {
        Path input = template.getPath();
        String pathStr = input.toString();
        String separator = File.separatorChar == '\\'
                ? "\\\\"
                : File.separator;
        String packagePath = packageToPath(properties.get("java.package"), separator).toString();
        if("\\\\".equals(separator)) {
            // we need even more slashes, or next replaceAll call will eat them alive
            packagePath = packagePath.replaceAll("\\\\", "\\\\\\\\");
        }
        pathStr = pathStr.replaceAll( separator + "?" + TEMPLATE_PACKAGE + separator, separator + packagePath + separator);
        return Paths.get(pathStr);
    }

    Path packageToPath(String packageName, String separator) {
        return Paths.get(packageName.replace(".", separator));
    }
}
