package be.tahayasin.menubook.Models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("language")
    @Expose
    private Language language;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_in_main_lang")
    @Expose
    private String nameInMainLang;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("description_in_main_lang")
    @Expose
    private String descriptionInMainLang;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image_id")
    @Expose
    private int imgId;
    @SerializedName("source_path")
    @Expose
    private String sourcePath;
    @SerializedName("source_path_in_main_lang")
    @Expose
    private String sourcePathInMainLang;
    @SerializedName("show_in_menu")
    @Expose
    private Boolean showInMenu;
    @SerializedName("show_in_kassa")
    @Expose
    private Boolean showInKassa;
    @SerializedName("show_in_site")
    @Expose
    private Boolean showInSite;
    @SerializedName("order")
    @Expose
    private String order;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameInMainLang() {
        return nameInMainLang;
    }

    public void setNameInMainLang(String nameInMainLang) {
        this.nameInMainLang = nameInMainLang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionInMainLang() {
        return descriptionInMainLang;
    }

    public void setDescriptionInMainLang(String descriptionInMainLang) {
        this.descriptionInMainLang = descriptionInMainLang;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgId() {
        return String.valueOf(imgId);
    }

    public void setImgId(String imgId) {
        this.imgId = Integer.valueOf(imgId);
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getSourcePathInMainLang() {
        return sourcePathInMainLang;
    }

    public void setSourcePathInMainLang(String  sourcePathInMainLang) {
        this.sourcePathInMainLang = sourcePathInMainLang;
    }

    public Boolean getShowInMenu() {
        return showInMenu;
    }

    public void setShowInMenu(Boolean showInMenu) {
        this.showInMenu = showInMenu;
    }

    public Boolean getShowInKassa() {
        return showInKassa;
    }

    public void setShowInKassa(Boolean showInKassa) {
        this.showInKassa = showInKassa;
    }

    public Boolean getShowInSite() {
        return showInSite;
    }

    public void setShowInSite(Boolean showInSite) {
        this.showInSite = showInSite;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
