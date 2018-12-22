package vyacheslav.volodko;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bios on 29.11.2017.
 */
public class FlatDAOImpl implements FlatDAO {

    private final Connection conn;

    public FlatDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void init() {
        try {
            Statement st = conn.createStatement();
            try {
                st.execute("DROP TABLE IF EXISTS Flats");
                st.execute("CREATE TABLE Flats (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, DISTRICT VARCHAR(20) NOT NULL, STREET VARCHAR(20) NOT NULL, ROOM INT, PRISE DOUBLE )");
            } finally {
                st.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void addFlat(String district, String street, int room, double prise) {
        try {
            try (PreparedStatement st = conn
                    .prepareStatement("INSERT INTO Flats (district, street, room, prise) VALUES(?, ?, ?, ?)")) {
                st.setString(1, district);
                st.setString(2, street);
                st.setInt(3, room);
                st.setDouble(4, prise);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteFlat(String street) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Flats WHERE street = ?")) {
            ps.setString(1, street);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Flat> getAll() {
        List<Flat> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM Flats")) {
                    while (rs.next()) {
                        Flat flat = new Flat();

                        flat.setId(rs.getInt(1));
                        flat.setDistrict(rs.getString(2));
                        flat.setStreet(rs.getString(3));
                        flat.setRoom(rs.getInt(4));
                        flat.getPrise(rs.getDouble(5));

                        res.add(flat);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public List<Flat> getByDistAndStr(String endistrict, String enstreet){
        List<Flat> flatsList = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats " +
                "WHERE district=? AND street=?")) {
            ps.setString(1, endistrict);
            ps.setString(2, enstreet);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String district;
                    String street;
                    int room;
                    double prise;
                    district = rs.getString(1);
                    street = rs.getString(2);
                    room = rs.getInt(3);
                    prise = rs.getDouble(4);
                    flatsList.add(new Flat (district, street, room, prise));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flatsList;
    }
}
