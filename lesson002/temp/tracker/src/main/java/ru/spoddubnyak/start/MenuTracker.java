package ru.spoddubnyak.start;

import ru.spoddubnyak.models.*;

class EditItem implements UserAction {
	public int key() {
			return 2;
		}
		
		public void execute (Input input, Tracker tracker) {
			String id = input.ask("Please, enter the task's id:> ");
			String name = input.ask("Please, enter the task's number:> ");
			String desc = input.ask("Please, enter the task's desc:> ");
			Task task = new Task(name, desc);
			task.setId(id);
			tracker.edit(task);
		}

	    public String info() {
			return String.format("%s. %s", this.key(), "Edit new item.");
		}
}

public class MenuTracker  {
	
	private Input input;
	private Tracker tracker;
	private UserAction[] actions =  new UserAction[5];
	
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}
	
	public void fillActions() {
		this.actions[0] = this.new AddItem();
		this.actions[1] = new MenuTracker.ShowItems();
		this.actions[2] = new EditItem();
		
	}

	public void show() {
		for (UserAction action : this.actions) {
			if (action != null) {
			  System.out.println(action.info());
			}
		}
	}

	public void select(int key) {
		this.actions[key].execute(this.input, this.tracker);
	}

	
	private class AddItem implements UserAction {
		public int key() {
			return 0;
		}
		
		public void execute (Input input, Tracker tracker) {
			String name = input.ask("Please, enter the task's number:> ");
			String desc = input.ask("Please, enter the task's desc:> ");
			tracker.add(new Task(name, desc));
		}

	    public String info() {
			return String.format("%s. %s", this.key(), "Add the new item.");
		}
	}
	
	
	private static class ShowItems implements UserAction {
		public int key() {
			return 1;
		}
		
		public void execute (Input input, Tracker tracker) {
			for (Item item : tracker.getAll()) {
			    System.out.println(
				    String.format("%s. %s", item.getId(), item.getName())
				);
			}
		}

	    public String info() {
			return String.format("%s. %s", this.key(), "Show all items.");
		}
	}
}