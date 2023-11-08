package ra.view;

import ra.config.Config;
import ra.controller.catalog.CataControllerIMPL;
import ra.model.Catalog;

import java.util.List;
import java.util.Scanner;

import static ra.config.Config.scanner;

public class CatalogView {
    public static CataControllerIMPL catacontroller = new CataControllerIMPL();
    public static List<Catalog> catalogList = catacontroller.findAll();

    public void showCatalog() {
        System.out.println("                                                                          \u001B[33mDanh Mục Sản Phẩm");
        System.out.format("                                                            +---------+-----------------+-----------------+%n");
        System.out.format("                                                            |    ID   |  Tên danh mục   |  Trạng thái     |%n");
        System.out.format("                                                            +---------+-----------------+-----------------+%n");
        for (Catalog cata : catalogList) {
            String leftAlignFormat = "                                                            | %-7d | %-15s | %-15s |%n";
            System.out.format(leftAlignFormat,cata.getId(),cata.getName(),cata.isStatus());
        }
        System.out.format("                                                            +---------+-----------------+-----------------+%n");

    }

    public void createCatalog() {
        Catalog newCatalog = new Catalog();
        if (catalogList.isEmpty()){
            newCatalog.setId(1);
        }else {
            newCatalog.setId(catalogList.get(catalogList.size()-1).getId()+1);
        }
        System.out.println("Nhập vào tên danh mục mới bạn muốn thêm: ");
        newCatalog.setName(Config.scanner().nextLine());
        System.out.println("Nhập vào trạng thái bạn muốn thêm: ");
        newCatalog.setStatus(Boolean.parseBoolean(Config.scanner().nextLine()));
        catacontroller.save(newCatalog);
        System.out.println("\033[0;33m                                                                       Thêm danh mục thành công!                                \033[0;0m");

    }

    public void updateCatalog() {
        showCatalog();
        System.out.println("Nhập id bạn muốn cập nhật: ");
        int id = Config.validateInt();
        if (catacontroller.findById(id) == null) {
            System.out.println("\033[0;31mId không tồn tại!\033[0;0m");
        } else {
            System.out.println("Nhập tên danh mục sản phẩm bạn muốn sửa: ");
            String nameUp = Config.scanner().nextLine();
            System.out.println("Nhập tên trạng thái danh mục muốn sửa: ");
            boolean status = Boolean.parseBoolean(Config.scanner().nextLine());
            Catalog newCatalog = new Catalog(id, nameUp, status);
            catacontroller.save(newCatalog);
            System.out.println("\033[0;33m                                                                     Cập nhật danh mục thành công!                                \033[0;0m");
        }
    }

    public void deleteCatalog(){
        System.out.println("Nhập id bạn muốn xóa: ");
        int id = Config.validateInt();
        catacontroller.deleteById(id);
        System.out.println("\033[0;33m                                                                       Xóa danh mục thành công!                                \033[0;0m");
    }

    public void searchByName(){
        System.out.println("Nhập tên danh mục bạn muốn tìm kiếm:");
        String searchName= Config.scanner().nextLine();
        System.out.format("\033[0;33m                                                            +---------+-----------------+-----------------+%n\033[0;0m");
        System.out.format("\033[0;33m                                                            |    ID   |  Tên danh mục   |  Trạng thái     |%n\033[0;0m");
        System.out.format("\033[0;33m                                                            +---------+-----------------+-----------------+%n\033[0;0m");
        for (Catalog c:catacontroller.searchByName(searchName)) {
            String leftAlignFormat = "\033[0;33m                                                            | %-7d | %-15s | %-15s |%n\033[0;0m";
            System.out.format(leftAlignFormat,c.getId(),c.getName(),c.isStatus());
        }
        System.out.format("\033[0;33m                                                            +---------+-----------------+-----------------+%n\033[0;0m");
    }

    public static void catalogManager(){
        int choice = 0;
        while (choice != 6){
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("\u001B[33m                                                                          " + "QUẢN LÍ DANH MỤC SẢN PHẨM" + "                                    \u001B[0m");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                1. Hiển thị danh mục sản phẩm                                                          ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                2. Tạo danh mục sản phẩm mới                                                   ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                3. Cập nhật danh mục sản phẩm                                                   ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                4. Xóa danh mục sản phẩm                                                   ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                5. Tìm kiếm danh mục sản phẩm                                                            ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                6. Thoát                                                                   ");
            System.out.println("\u001B[35m                                         ---------------------------------------------------------------------------------------\u001B[0m");
            System.out.println("                                                 Lựa chọn của bạn là:                                                           ");

            choice = Config.validateInt();
            switch (choice) {
                case 1:
                    new CatalogView().showCatalog();
                    break;
                case 2:
                    new CatalogView().createCatalog();
                    break;
                case 3:
                    new CatalogView().updateCatalog();
                    break;
                case 4:
                    new CatalogView().deleteCatalog();
                    break;
                case 5:
                    new CatalogView().searchByName();
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
