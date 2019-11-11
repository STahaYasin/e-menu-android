package be.tahayasin.menubook.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class HoofdModel {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("image_id")
        @Expose
        private String sourcePath;
        @SerializedName("menus")
        @Expose
        private List<Menu> menus = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSourcePath() {
            return sourcePath;
        }

        public void setSourcePath(String sourcePath) {
            this.sourcePath = sourcePath;
        }

        public List<Menu> getMenus() {
            return menus;
        }

        public void setMenus(List<Menu> menus) {
            this.menus = menus;
        }
    }
