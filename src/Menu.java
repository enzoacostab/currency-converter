import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Options options;
    private final Scanner in = new Scanner(System.in);
    private double valueSelected = 0;
    private int optionSelected = 0;
    private final Converter converter = new Converter();
    private final List<String> history = new ArrayList<>();
    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public Menu (Options options) {
        this.options = options;
    }

    public void showMenu() {
        System.out.println("_".repeat(47));
        for (int i = 0; i < options.size(); i++) {
            CurrencyCode[] option = options.get(i);
            String space = (i < 10) ? " ".repeat(8) : " ".repeat(7);
            if (i % 2 != 0) {
                System.out.println("   |   " + i + ")" + space + option[0] + " -> " + option[1]);
            } else {
                System.out.print(i + ")" + space + option[0] + " -> " + option[1]);
            }
        }
        System.out.println(options.size() + ")  show conversion history");
        System.out.println((options.size() + 1) + ")  exit");
        System.out.println("_".repeat(47));
    }

    public int selectOption() {
        System.out.println("choose a option:");

        while (true) {
            try {
                optionSelected = Integer.parseInt(in.nextLine());
                if (optionSelected < 0 || optionSelected > options.size() + 1) {
                    System.out.println("choose a valid option:");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("choose a valid option:");
            }
        }

        return optionSelected;
    }

    public void selectValue() {
        System.out.println("enter the value you want to convert:");

        while (true) {
            try {
                valueSelected = Double.parseDouble(in.nextLine());
                if (valueSelected < 0) {
                    System.out.println("enter a valid value:");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("enter a valid value:");
            }
        }
    }

    public boolean exit() {
        System.out.println("""
        
        Do you want to continue?
        1) yes
        2) no
        """);

        while (true) {
            try {
                int exitOptionSelected = Integer.parseInt(in.nextLine());
                if (exitOptionSelected != 1 && exitOptionSelected != 2) {
                    System.out.println("choose a valid option:");
                } else return exitOptionSelected == 2;
            } catch (Exception e) {
                System.out.println("choose a valid option:");
            }
        }
    }

    public void showCoversion() {
        CurrencyCode[] option = options.get(optionSelected);
        double conversion = converter.convert(option[0], option[1], valueSelected);
        String text = valueSelected + " " + option[0] + " = " + decimalFormat.format(conversion) + " " + option[1];
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
        history.add(text + " - " + dateTime.format(dateTimeFormatter));
        System.out.println();
        System.out.println(text);
    }

    public void showHistory() {
        System.out.println();
        System.out.println("conversion history");
        for (String i : history) {
            System.out.println(i);
        }
    }
}
