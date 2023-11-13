package ra.view;

import ra.config.Config;
import ra.controller.catalog.CataControllerIMPL;
import ra.controller.product.ProductControllerIMPL;
import ra.model.Catalog;
import ra.model.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static ra.config.Config.scanner;

public class ProductView {
    ProductControllerIMPL productcontroller = new ProductControllerIMPL();
    List<Product> productList = productcontroller.findAll();

    public void showProduct(){
        System.out.println("                                                                     \u001B[33mDanh Sách Sản Phẩm KangNam Shop");
        System.out.format("                                          +---------+---------------------+---------------+-------------------+----------------+%n");
        System.out.format("                                          |    ID   |     Tên sản phẩm    |    Danh mục   |    Giá sản phẩm   |   Trạng thái   |%n");
        System.out.format("                                          +---------+---------------------+---------------+-------------------+----------------+%n");
        String leftAlignFormat = "                                          | %-7d | %-19s | %-13s |  %-15s  | %-6s |%n";
        for (Product listproduct:productList) {
            if (listproduct.getProductCatalog().isStatus()){
            System.out.format(leftAlignFormat,listproduct.getProductId(),listproduct.getProductName(),listproduct.getProductCatalog().getName(),Config.formatMoney.format(listproduct.getProductPrice()),(listproduct.getProductStatus()?"   Đang bán   ":"   Ngừng bán  "));
            }
        }
        System.out.format("                                          +---------+---------------------+---------------+-------------------+----------------+%n");

    }

    public void showProductAdmin(){
        System.out.println("                                                                     \u001B[33mDanh Sách Sản Phẩm KangNam Shop");
        System.out.format("                                     +---------+---------------------+---------------+-------------------+----------------+----------+%n");
        System.out.format("                                     |    ID   |     Tên sản phẩm    |    Danh mục   |    Giá sản phẩm   |   Trạng thái   | Số lượng +%n");
        System.out.format("                                     +---------+---------------------+---------------+-------------------+----------------+----------+%n");
        String leftAlignFormat = "                                     | %-7d | %-19s | %-15s |  %-15s  | %-6s | %-7d  |%n";
        for (Product listproduct:productList) {
            System.out.format(leftAlignFormat,listproduct.getProductId(),listproduct.getProductName(),listproduct.getProductCatalog().getName(),Config.formatMoney.format(listproduct.getProductPrice()),(listproduct.getProductStatus()?"   Còn hàng   ":" ( Hết hàng ) "), listproduct.getStock());
        }
        System.out.format("                                     +---------+---------------------+---------------+-------------------+----------------+----------+%n");

    }

    public void createProduct(){
            System.out.println("Nhập số lượng sản phẩm muốn thêm mới: ");
            int quan = Config.validateInt();

            if (productList.size() + quan > 50) {
                System.out.println("\033[0;31m                                                          Không thể thêm 1 lúc quá nhiều sản phẩm.                                \033[0;0m");
                return;
            } else if (quan == 0) {
                System.out.println("\033[0;31m                                                                   Vui lòng nhập lớn hơn 1.                                \033[0;0m");
            }
            for (int i = 0; i < quan; i++) {
                Product product = new Product();
                if (productList.isEmpty()) {
                    product.setProductId(1);
                } else {
                    product.setProductId(productList.get(productList.size() - 1).getProductId() + 1);
                }
                Catalog catalog = null;
                List<Catalog> catalogList = new CataControllerIMPL().findAll();
                System.out.println("Thông tin danh mục sản phẩm");
                System.out.format("\033[0;33m                                                            +---------+-----------------+-----------------+%n\033[0;0m");
                System.out.format("\033[0;33m                                                            |    ID   |  Tên danh mục   |  Trạng thái     |%n\033[0;0m");
                System.out.format("\033[0;33m                                                            +---------+-----------------+-----------------+%n\033[0;0m");
                for (Catalog c : catalogList) {
                    String leftAlignFormat = "\033[0;33m                                                            | %-7d | %-15s | %-15s |%n\033[0;0m";
                    System.out.format(leftAlignFormat, c.getId(), c.getName(), c.isStatus());
                }
                System.out.format("\033[0;33m                                                            +---------+-----------------+-----------------+%n\033[0;0m");


                System.out.println("Chọn danh mục sản phẩm theo id: ");
                int idC = Config.validateInt();
                if (idC >= 1 && idC <= catalogList.size()) {
                    catalog = catalogList.get(idC - 1);
                } else {
                    System.out.println("\033[0;31m                                                           Danh mục không tồn tại, mời tạo mới danh mục hoặc chọn lại.                                \033[0;0m");
                    return;
                }

                for (Catalog c : catalogList) {
                    if (c.getId() == idC) {
                        catalog = c;
                    }
                }
                product.setProductCatalog(catalog);
                System.out.println("Nhập tên sản phẩm cần thêm mới: ");
                product.setProductName(Config.scanner().nextLine());
                System.out.println("Nhập giá sản phẩm:");
                product.setProductPrice(Float.parseFloat(Config.scanner().nextLine()));
                System.out.println("Nhập trạng thái sản phẩm: ");
                product.setProductStatus(Boolean.parseBoolean(Config.scanner().nextLine()));
                System.out.println("Nhập số lượng trong kho: ");
                product.setStock(Config.validateInt());
                productcontroller.save(product);
                System.out.println("\033[0;33m                                                                       Thêm sản phẩm thành công!                                \033[0;0m");
            }
    }


    public void deleteProduct(){
        System.out.println("Nhập id sản phẩm cần xóa: ");
        int id = Config.validateInt();
        productcontroller.delete(id);
        System.out.println("\033[0;33m                                                                       Xóa sản phẩm thành công!                                \033[0;0m");
    }

    public void searchByName(){
        System.out.println("Nhập tên sản phẩm bạn muốn tìm kiếm: ");
        String searchName= Config.scanner().nextLine();
        System.out.format("\u001B[33m                                          +---------+---------------------+-----------------+-------------------+----------------+%n\u001B[0m");
        System.out.format("\u001B[33m                                          |    ID   |     Tên sản phẩm    |     Danh mục    |    Giá sản phẩm   |   Trạng thái   |%n\u001B[0m");
        System.out.format("\u001B[33m                                          +---------+---------------------+-----------------+-------------------+----------------+%n\u001B[0m");
        String leftAlignFormat = "\u001B[33m                                          | %-7d |  %-15s    | %-15s |  %-15s  | %-6s |%n\u001B[0m";
        for (Product p:productcontroller.searchByName(searchName)) {
            System.out.format(leftAlignFormat,p.getProductId(),p.getProductName(),p.getProductCatalog().getName(),Config.formatMoney.format(p.getProductPrice()),(p.getProductStatus()?"Đang hoạt động":"  ( Đang ẩn ) "));
        }
        System.out.format("\u001B[33m                                          +---------+---------------------+-----------------+-------------------+----------------+%n\u001B[0m");
    }

    public void updateProduct(){
        showProductAdmin();
        System.out.println("Nhập id của sản phẩm bạn muốn cập nhật: ");
        int idUpdate = Config.validateInt();
        if (productcontroller.findById(idUpdate)==null){
            System.out.println("\033[0;31m Id không tồn tại !\033[0;0m");
        }else {

            System.out.println("Nhập tên sản phẩm bạn muốn thay đổi: ");
            String nameUpdate = Config.scanner().nextLine();
            Catalog catalogUpdate = null;
            List<Catalog> catalogList = new CataControllerIMPL().findAll();
            for (Catalog c:catalogList) {
                System.out.println(c);
            }
            System.out.println("Nhập id danh mục sản phẩm: ");
            int idC = Config.validateInt();
            for (Catalog c:catalogList) {
                if (c.getId()==idC){
                    catalogUpdate = c;
                }
            }
            System.out.println("Nhập giá sản phẩm cần thay đổi: ");
            Float priceUpdate = Float.parseFloat(Config.scanner().nextLine());
            System.out.println("Nhập trạng thái cần thay đổi: ");
            Boolean statusUpdate = Boolean.parseBoolean(Config.scanner().nextLine());
            System.out.println("Nhập số lượng trong kho: ");
            int stockUpdate = Config.validateInt();
            Product productUpdate = new Product(idUpdate,catalogUpdate,nameUpdate,priceUpdate,statusUpdate,stockUpdate);
            productcontroller.save(productUpdate);
        System.out.println("\033[0;33m                                                                       Cập nhật sản phẩm thành công!                                \033[0;0m");
        }
    }
    public void featuredProduct(){
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return (int) (o2.getProductPrice() - o1.getProductPrice());
            }
        });
        System.out.println("                                                                    \u001B[33mSản Phẩm Nổi Bật Của KangNam Shop");
        System.out.format("                                          +---------+---------------------+--------------+-------------------+----------------+%n");
        System.out.format("                                          |    ID   |     Tên sản phẩm    |   Danh mục   |    Giá sản phẩm   |   Trạng thái   |%n");
        System.out.format("                                          +---------+---------------------+--------------+-------------------+----------------+%n");
        String leftAlignFormat = "                                          | %-7d | %-19s | %-12s |  %-15s  | %-6s |%n";
        for (Product listproduct:productList) {
            if (listproduct.getProductCatalog().isStatus()){
                System.out.format(leftAlignFormat,listproduct.getProductId(),listproduct.getProductName(),listproduct.getProductCatalog().getName(),Config.formatMoney.format(listproduct.getProductPrice()),(listproduct.getProductStatus()?"   Đang bán   ":"   Ngừng bán  "));
            }
        }
        System.out.format("                                          +---------+---------------------+--------------+-------------------+----------------+%n");

    }

    public static void ProductManagement(){
        int c = 0;
        while (c != 6) {

            System.out.println("\u001B[35m                                         -----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("\u001B[33m                                                                          " + "QUẢN LÍ SẢN PHẨM" + "                                           \u001B[0m");
            System.out.println("\u001B[35m                                         -----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                1. Hiển thị danh sách sản phẩm                                                         ");
            System.out.println("\u001B[35m                                         -----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                2. Thêm sản phẩm mới                                                   ");
            System.out.println("\u001B[35m                                         -----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                3. Cập nhật sản phẩm                                                  ");
            System.out.println("\u001B[35m                                         -----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                4. Xóa sản phẩm                                                   ");
            System.out.println("\u001B[35m                                         -----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                5. Tìm kiếm sản phẩm theo tên                                                           ");
            System.out.println("\u001B[35m                                         -----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                6. Thoát                                                                   ");
            System.out.println("\u001B[35m                                         -----------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                 Lựa chọn của bạn là:                                                           ");

            c = Config.validateInt();
            switch (c){
                case 1:
                    new ProductView().showProductAdmin();
                    break;
                case 2:
                    new ProductView().createProduct();
                    break;
                case 3:
                    new ProductView().updateProduct();
                    break;
                case 4:
                    new ProductView().deleteProduct();
                    break;
                case 5:
                    new ProductView().searchByName();
                    break;
                case 6:
                    new Navbar();
                    Navbar.generalManager();
                    break;

                default:
                    System.out.println("\033[0;31m                                                                         Không hợp lệ. Vui lòng chọn lại.                                \033[0;0m");
                    break;
            }
        }
    }


}
