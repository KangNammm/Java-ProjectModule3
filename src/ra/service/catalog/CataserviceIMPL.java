package ra.service.catalog;

import ra.config.Config;
import ra.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CataserviceIMPL implements ICataService {

    public static List<Catalog> catalogList = new Config<Catalog>().readFromFile(Config.PATH_CATALOG);

    @Override
    public List<Catalog> findAll() {
        return catalogList;
    }

    @Override
    public void save(Catalog catalog) {
        if (findById(catalog.getId()) == null) {
            catalogList.add(catalog);
        } else {
            catalogList.set(catalogList.indexOf(findById(catalog.getId())), catalog);
        }
        new Config<Catalog>().writeToFile(Config.PATH_CATALOG,catalogList);
    }

    @Override
    public Catalog findById(int id) {
        for (Catalog findCata : catalogList
        ) {
            if (findCata.getId() == id) {
                return findCata;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < catalogList.size(); i++) {
            if (catalogList.get(i).getId() == id ){
                catalogList.remove(i);
                new Config<Catalog>().writeToFile(Config.PATH_CATALOG,catalogList);
                return;
            }
        }
        System.out.println("Id không tồn tại");
    }

    public List<Catalog> searchByName(String searchName) {
        List<Catalog> catalogListSearch = new ArrayList<>();
        for (Catalog catalog:catalogList) {
            if (catalog.getName().toLowerCase().contains(searchName.toLowerCase())){
                catalogListSearch.add(catalog);
            }
        }
        return catalogListSearch;
    }

    @Override
    public boolean changeUserStatus(int id) {
        return false;
    }
}
