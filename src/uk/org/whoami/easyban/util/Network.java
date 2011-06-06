/*
 * Copyright 2011 Sebastian Köhler <sebkoehler@whoami.org.uk>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.org.whoami.easyban.util;

public class Network {

    public static boolean isValidSubnet(String sub) {
        String[] sp = sub.split("/");
        if (sp.length != 2) {
            return false;
        }
        if (sp[0].split("\\.").length != 4) {
            return false;
        }
        if (sp[1].split("\\.").length != 4) {
            return false;
        }

        return true;
    }

    public static boolean isIpInSubnet(String ips, String subnet) {
        String[] sp = subnet.split("/");
        return isIpInSubnet(ips, sp[0],sp[1]);
    }

    public static boolean isIpInSubnet(String ips, String nets, String masks) {
        String[] ip = ips.split("\\.");
        String[] net = nets.split("\\.");
        String[] mask = masks.split("\\.");

        for (int i = 0; i < 4; i++) {
            int tmp = 255 - Integer.parseInt(mask[i]);
            if (Integer.parseInt(ip[i]) - Integer.parseInt(net[i]) > tmp
                    || Integer.parseInt(ip[i]) - Integer.parseInt(net[i]) < 0) {
                return false;
            }
        }
        return true;
    }
}
