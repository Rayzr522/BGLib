
package com.rayzr522.bglib.utils;

import com.rayzr522.bglib.plugin.MinigamePlugin;
import com.rayzr522.bitzapi.BitzPlugin;

public class MinigameUtils {

    public static boolean isMGPlugin(BitzPlugin plugin) {

        return plugin.getClass().getSuperclass() == MinigamePlugin.class;

    }

}
