import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static HashMap<String, String> pb = new HashMap<>();

    private static void saveTS() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("phone.txt")))) {
            for (Map.Entry<String, String> k : pb.entrySet()) {
                writer.write(k.getKey() + " " + k.getValue() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("Не удалось сохранить изменения.");
        }
    }

    private static boolean FoundName(String name) {
        boolean flag = false;
        for (Map.Entry entry : pb.entrySet())
            if (entry.getValue().equals(name)) {
                System.out.println((entry.getValue() + ": " + entry.getKey()));
                flag = true;
            }
        return flag;
    }

    private static boolean FoundPhone(String phone) {
        for (Map.Entry entry : pb.entrySet())
            if (entry.getKey().equals(phone)) {
                System.out.println((entry.getValue() + ": " + entry.getKey()));
                return true;
            }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String act;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("phone.txt")))) {

            while ((act = reader.readLine()) != null) {
                String[] dat = act.split(" ");
                pb.put(dat[0], dat[1]);
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("Справочник пустой.");
        }

        System.out.println("Выберите действие: " +
                "\n 1 - посмотреть все записи" +
                "\n 2 - найти по имени" +
                "\n 3 - найти по номеру" +
                "\n 4 - добавить запись" +
                "\n 5 - удалить запись" +
                "\n 6 - сохранить изменения" +
                "\n 7 - выход");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        act = bf.readLine();
        while (!act.equals("7")) {
            if (act.equals("1")) {
                for (Map.Entry<String, String> k : pb.entrySet())
                    System.out.println(k.getValue() + ": " + k.getKey());
            } else {
                if (act.equals("2")) {
                    System.out.println("Введите имя:");
                    String name = bf.readLine();
                    if (!FoundName(name))
                        System.out.println("Имя не найдено.");
                } else {
                    if (act.equals("3")) {
                        System.out.println("Введите номер:");
                        String phone = bf.readLine();//!!!
                        if (!FoundPhone(phone))
                            System.out.println("Такой номер не существует.");
                    } else {
                        if (act.equals("4")) {
                            System.out.println("Введите имя:");
                            String name = bf.readLine();
                            System.out.println("Введите телефон:");
                            String phone = bf.readLine();
                            if (name.length() > 0 && phone.length() > 0)
                                pb.put(phone, name);
                            else
                                System.out.println("Введены не все значения");
                        } else {
                            if (act.equals("5")) {
                                System.out.println("Введите телефон:");
                                String phone = bf.readLine();
                                pb.remove(phone);
                            } else {
                                if (act.equals("6")) {
                                    saveTS();
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Выберите действие: " +
                    "\n 1 - посмотреть все записи" +
                    "\n 2 - найти по имени" +
                    "\n 3 - найти по номеру" +
                    "\n 4 - добавить запись" +
                    "\n 5 - удалить запись" +
                    "\n 6 - сохранить изменения" +
                    "\n 7 - выход");
            act = bf.readLine();
        }
    }
}

