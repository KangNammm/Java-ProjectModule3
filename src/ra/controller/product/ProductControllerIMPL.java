package ra.controller.product;

import ra.model.Product;
import ra.service.catalog.ICataService;
import ra.service.product.IProductService;
import ra.service.product.ProductServiceIMPL;

import java.util.List;

public class ProductControllerIMPL implements IProductController {

    private static IProductService productservice = new ProductServiceIMPL();

    @Override
    public List<Product> findAll() {
        return productservice.findAll();
    }

    @Override
    public void save(Product product) {
        productservice.save(product);
    }

    @Override
    public Product findById(int id) {
        return productservice.findById(id);
    }

    @Override
    public void delete(int id) {
        productservice.deleteById(id);
    }

    @Override
    public List<Product> searchByName(String searchName) {
        return productservice.searchByName(searchName);
    }
}
