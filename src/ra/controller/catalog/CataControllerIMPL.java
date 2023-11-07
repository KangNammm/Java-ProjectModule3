package ra.controller.catalog;

import ra.model.Catalog;
import ra.service.catalog.CataserviceIMPL;
import ra.service.catalog.ICataService;

import java.util.List;

public class CataControllerIMPL implements ICataService {

    private static CataserviceIMPL catalogservice = new CataserviceIMPL();
    private static List<Catalog> catalogList = catalogservice.findAll();

    @Override
    public List<Catalog> findAll() {
        return catalogList;
    }

    @Override
    public void save(Catalog catalog) {
        catalogservice.save(catalog);
    }

    @Override
    public Catalog findById(int id) {
        return catalogservice.findById(id);
    }

    @Override
    public void deleteById(int id) {
        catalogservice.deleteById(id);
    }

    public List<Catalog> searchByName(String searchName) {
        return catalogservice.searchByName(searchName);

    }

    @Override
    public boolean changeUserStatus(int id) {
        return false;
    }
}
