package config;

public enum BasePaths {

    SINGLE_CASE("casetype/single"),
    BULK_CASE("casetype/bulk");

    private final String basePath;

    BasePaths(String path) {
        this.basePath = path;
    }

    public String getPath() {
        return basePath;
    }

}
