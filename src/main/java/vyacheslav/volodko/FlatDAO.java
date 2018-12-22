package vyacheslav.volodko;

import java.util.List;

public interface FlatDAO {
    void init();
    void addFlat(String district, String street, int room, double prise);
    void deleteFlat(String street);
    List<Flat> getAll();
    List<Flat> getByDistAndStr(String district, String street);
}
