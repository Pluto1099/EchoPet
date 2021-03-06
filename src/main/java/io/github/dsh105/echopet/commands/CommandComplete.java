package io.github.dsh105.echopet.commands;

import io.github.dsh105.echopet.EchoPet;
import io.github.dsh105.echopet.data.PetType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * Project by DSH105
 */

public class CommandComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> list = new ArrayList<String>();
        String cmdString = EchoPet.getPluginInstance().cmdString;
        if (cmd.getName().equalsIgnoreCase(cmdString)) {
            String[] completions;
            if (args.length >= 2) {
                completions = getCompletions(args.length, args[args.length - 2]);
            } else {
                completions = getCompletions(args.length);
            }
            for (String s : completions) {
                if (s.startsWith(args[args.length - 1])) {
                    list.add(s);
                }
            }
        }
        return list;
    }

    private String[] getCompletions(int i) {
        switch (i) {
            case 0:
                return new String[]{EchoPet.getPluginInstance().cmdString, EchoPet.getPluginInstance().adminCmdString};
            case 1:
                return new String[]{"bat", "blaze", "cavespider", "chicken", "cow", "creeper", "enderdragon",
                        "enderman", "ghast", "horse", "irongolem", "magmacube", "mushroomcow", "ocelot", "pig",
                        "pigzombie", "sheep", "silverfish", "skeleton", "slime", "snowman", "spider", "squid",
                        "villager", "witch", "wither", "wolf", "zombie", "name", "mount", "list", "info", "default",
                        "ride", "hat", "call", "show", "hide", "menu", "select", "remove"};
        }
        return new String[0];
    }

    private String[] getCompletions(int i, String argBefore) {
        switch (i) {
            case 0:
                return getCompletions(i);
            case 1:
                return getCompletions(i);
            case 2:
                ArrayList<String> list = new ArrayList<String>();
                for (PetType pt : PetType.values()) {
                    if (argBefore.equalsIgnoreCase(pt.toString().toLowerCase())) {
                        list.add(pt.toString().toLowerCase());
                    }
                }
                if (argBefore.equalsIgnoreCase("name")) {
                    list.add("Pet");
                    list.add("mount");
                } else if (argBefore.equalsIgnoreCase("mount")) {
                    list.add("remove");
                    for (PetType pt : PetType.values()) {
                        list.add(pt.toString().toLowerCase());
                    }
                } else if (argBefore.equalsIgnoreCase("default")) {
                    list.add("set");
                    list.add("remove");
                }
                return list.toArray(new String[list.size()]);
        }
        return new String[0];
    }
}