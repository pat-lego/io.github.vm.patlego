package io.github.vm.patlego.enc.commands;

import org.apache.commons.lang3.StringUtils;
import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;

import io.github.vm.patlego.enc.Security;

@Service
@Command(scope = "patlego", name = "encrypt", description = "Encrypt a string using Security service")
public class Encrypt implements Action {

    @Reference
    private Security security;

    @Option(name = "-i", aliases = "--input", description = "Input string to be encrypted", required = true, multiValued = false)
    String input = StringUtils.EMPTY;

    @Override
    public Object execute() throws Exception {
        return security.encrypt(input);
    }

}