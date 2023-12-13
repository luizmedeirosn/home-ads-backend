package com.luizmedeirosn.homeads.entities.enums;

public enum AdCategoryEnum {

    BED_AND_BATH(1),
    APPLIANCES(2),
    FURNITURE(3),
    TOOLS(4);

    private int code;

    private AdCategoryEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AdCategoryEnum valueOf(int code) {
        for (AdCategoryEnum value : AdCategoryEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid AD Category code");
    }

}
