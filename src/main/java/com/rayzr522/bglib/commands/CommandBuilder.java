
package com.rayzr522.bglib.commands;

import java.util.List;

import com.rayzr522.bitzapi.commands.BitzCommand;
import com.rayzr522.bitzapi.commands.CommandHandler;

public class CommandBuilder {

	private CommandHandler handler;

	private CommandBuilder(CommandHandler handler) {

		this.handler = handler;

	}

	public static CommandBuilder create(CommandHandler handler) {

		return new CommandBuilder(handler);

	}

	public CommandBuilder add(Class<? extends BitzCommand> commandClass) {

		handler.registerCommand(commandClass);

		return this;

	}

	public CommandBuilder addList(List<Class<? extends BitzCommand>> commandClasses) {

		for (Class<? extends BitzCommand> clazz : commandClasses) {

			add(clazz);

		}

		return this;

	}

	public CommandBuilder add(List<Class<? extends BitzCommand>> commandClasses) {

		if (commandClasses.size() > 0) {

			for (Class<? extends BitzCommand> commandClass : commandClasses) {

				handler.registerCommand(commandClass);

			}

		}

		return this;

	}

	public CommandHandler build() {

		return handler;

	}

}
