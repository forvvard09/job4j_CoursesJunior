package ru.spoddubnyak.start;

public class StartUI {
	private Input input;
	private int[] ranges = new int[] {1,2,3,4};
	
	public StartUI(Input input) {
		this.input = input;
	}
	
    public void init() {
		Tracker tracker = new Tracker();
		MenuTracker menu = new MenuTracker(this.input, tracker);
		menu.fillActions();
		do {
			menu.show();
			menu.select(input.ask("select:", ranges));
		} while(!"y".equals(this.input.ask("Exit?(y): ")));
	}
	
	public static void main(String[] args) {
		//Input input = new StubInput(new String[] {"create stub task"})
		Input input = new ValidateInput();
		new StartUI(input).init();    
    }
}

