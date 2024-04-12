public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        Menu menu = new Menu(options);

        while (true) {
            menu.showMenu();
            int optionSelected = menu.selectOption();
            if (optionSelected == options.size() + 1) break;
            if (optionSelected == options.size()) {
                menu.showHistory();
            } else {
                menu.selectValue();
                menu.showCoversion();
            }
            if (menu.exit()) break;
        }
    }
}
