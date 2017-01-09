package ru.spoddubnyak.start;

import org.junit.Test;
import ru.spoddubnyak.models.Item;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class test to emulate the user experience through the console.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 05.01.2017
 */
public class StartUITest {
    /**
     * property - the user interaction method.
     */
    private Input input;
    /**
     * property - the array of possible answers.
     */
    private static String[] answers = {"1","2","3","4","5","6","7","8","test","q"};

    /** Constructor it creates a new object with the specified values.
     * @param input - interface class to communicate via the console
     */
    public StartUITest(Input input) {
        this.input = input;
    }
    /*
    public static void main(String[] args) {
        Input input = new StubInput(answers);
        new StartUITest(input).init();
    }
    */

    /**
     * Test method writes and read a list of available actions menu.
     */

    public void init() {
        Tracker tracker = new Tracker();
        Menu menu = new Menu();
        choiceAction(menu, tracker);
    }

    public void choiceAction(Menu menu, Tracker tracker) {
        String answer;
        do {
            menu.showMenuActions();
            System.out.println("------");
            answer = input.ask("Select a menu item :> ");
            if (!answer.equals("q")) {
                action(answer, tracker);
            }

            System.out.println(System.getProperty("line.separator"));
        } while (!answer.equals("q"));
    }

    public void action(String menuItem, Tracker tracker) {
        switch (menuItem) {
            case "1":
                System.out.println("Add a new item in the tracker:");
                String nameItem = input.ask("Enter name Item :> ");
                String descreptionItem = input.ask("Enter descreption Item :> ");
                Long createItem = Long.parseLong(input.ask("Enter create Item in formating Long :> "));
                System.out.println("-----");
                tracker.add(new Item(nameItem, descreptionItem, createItem));
                break;

            case "2":
                System.out.println("Update item in tracker:");
                int idItem = Integer.parseInt(input.ask("Enter id Item :>"));
                String nameNewItem = input.ask("Enter name new Item :> ");
                String descreptionNewItem = input.ask("Enter descreption new Item :> ");
                Long createNewItem = Long.parseLong(input.ask("Enter create Item in formating Long :> "));
                tracker.update(new Item(idItem, nameNewItem, descreptionNewItem, createNewItem));
                System.out.println("-----");
                break;

            case "3":
                System.out.println("Delete item in tracker:");
                idItem = Integer.parseInt(input.ask("Enter id Item delete :>"));
                tracker.delete(tracker.findById(idItem));
                System.out.println("-----");
                break;

            case "4":
                System.out.println("Find all item's in tracker:");
                System.out.println("-----");
                for (Item item : tracker.findAll()) {
                    System.out.println(item.getId() + "--" + item.getName() + "--" + item.getDescription() + "--" + item.getCreate());
                }
                System.out.println("-----");
                break;

            case "5":
                System.out.println("Find item by name in tracker:");
                String key = input.ask("Enter key for find by name in Tracker :> ");
                System.out.println("-----");
                for (Item item : tracker.findByName(key)) {
                    System.out.println(item.getId() + "--" + item.getName() + "--" + item.getDescription() + "--" + item.getCreate());
                }
                System.out.println("-----");
                break;

            case "6":
                System.out.println("Find item by id in tracker:");
                int id = Integer.parseInt(input.ask("Enter id for find by id Item in Tracker :> "));
                Item itemFindId = tracker.findById(id);
                System.out.println("-----");
                System.out.println(itemFindId.getId() + "--" + itemFindId.getName() + "--" + itemFindId.getDescription() + "--" + itemFindId.getCreate());
                System.out.println("-----");
                break;

            default:
                System.out.print(System.getProperty("line.separator"));
                System.out.print("Error. You have entered an invalid character. Repeat the entry or press 'q' to exit the program.");
                break;
        }
    }

    @Test
    public void whenMenu1() {
        Input input = new StubInput(answers);
        new StartUITest(input).init();

    }

}
