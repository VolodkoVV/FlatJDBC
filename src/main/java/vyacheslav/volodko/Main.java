package vyacheslav.volodko;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/mydb";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "sl11on24";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        ConnectionFactory factory = new ConnectionFactory(
                DB_CONNECTION, DB_USER, DB_PASSWORD
        );

        Connection conn = factory.getConnection();
        try {
            FlatDAO dao = new FlatDAOImpl(conn);
            dao.init();

            //...

            while (true) {
                System.out.println("1: add flat");
                System.out.println("2: view flats");
                System.out.println("3: delete flats");
                System.out.println("4: View by district and street flats");
                System.out.print("-> ");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        System.out.print("Enter flat district: ");
                        String district = sc.nextLine();
                        System.out.print("Enter flat street: ");
                        String street = sc.nextLine();
                        System.out.print("Enter client room: ");
                        String rooms = sc.nextLine();
                        System.out.print("Enter client prise: ");
                        String prises = sc.nextLine();
                        int room = Integer.parseInt(rooms);
                        double prise = Integer.parseInt(prises);

                        dao.addFlat(district, street, room, prise);

                        break;
                    case "2":
                        List<Flat> list = dao.getAll();
                        for (Flat flat : list) {
                            System.out.println(flat);
                        }
                        break;
                    case "3": {
                        System.out.println("Enter street");
                        String streets = sc.nextLine();
                        dao.deleteFlat(streets);
                        break;

                    }
                    case "4":{
                        System.out.println("Enter district");
                        String district1 = sc.nextLine();
                        System.out.println("Enter street");
                        String street1 = sc.nextLine();
                        System.out.println(dao.getByDistAndStr(district1,street1));
                        break;
                    }
                    default:
                        return;
                }
            }
        } finally {
            sc.close();
            if (conn != null) conn.close();
        }
    }

}
