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
package uk.org.whoami.easyban.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import uk.org.whoami.easyban.ConsoleLogger;
import uk.org.whoami.easyban.datasource.DataSource;
import uk.org.whoami.easyban.util.Subnet;

public class UnbanSubnetCommand extends EasyBanCommand {
    
    private DataSource database;

    public UnbanSubnetCommand(DataSource database) {
        this.database = database;
    }

    @Override
    protected void execute(CommandSender cs, Command cmnd, String cmd, String[] args) {
        if (args.length == 0) {
                return;
            }
            Subnet subnet = null;
            try {
                subnet = new Subnet(args[0]);
            } catch (IllegalArgumentException ex) {
            }

            if (subnet != null) {
                database.unbanSubnet(subnet);
                cs.getServer().broadcastMessage(args[0] + m._(" has been unbanned"));
                ConsoleLogger.info(args[0] + " has been unbanned by " + admin);
            } else {
                cs.sendMessage(m._("Invalid Subnet"));
            }
    }
    
}
