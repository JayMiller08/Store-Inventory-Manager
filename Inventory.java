public class Inventory {
    String productName, productCategory;
    double productPrice;

    public Inventory( String productName, String productCategory, double productPrice ){
        this.productName = productName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
    }

    public void setProductName(){
        this.productName = productName;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName(){
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public double getProductPrice(){
        return productPrice;
    }

    @Override
    public String toString(){
        return "Product: "+productName+"\t\tPrice: "+productPrice+"\t-----Category: "+productCategory;
    }

}
