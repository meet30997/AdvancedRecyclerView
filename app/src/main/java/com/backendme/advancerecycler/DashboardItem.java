package com.backendme.advancerecycler;

/**
 * The {@link DashboardItem} class.
 * <p>Defines the attributes for a restaurant menu item.</p>
 */
public class DashboardItem {

    private final String name;
    private final String price;
    private final String imageName;

    public DashboardItem(String name , String price,
                         String imageName) {
        this.name = name;
        this.price = price;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }


    public String getPrice() {
        return price;
    }


    public String getImageName() {
        return imageName;
    }
}
