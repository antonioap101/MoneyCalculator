package software.ulpgc.moneycalculator.model;

public record Currency(String code, String name) {
    @Override
    public String toString() {
        return code + "-" + name;
    }
}
