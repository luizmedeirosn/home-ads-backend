package com.luizmedeirosn.homeads.shared.enums;

public enum AdCategoryEnum {
    
    BETH_AND_BATH (1),
    APPLIANCES (2),
    FURNITURE (3),
    TOOLS (4);

    private int code; // Código do tipo enumerado;

    private AdCategoryEnum(int code) { // O construtor é private devido a ser um caso especial;
        this.code = code;
    }

    public int getCode() { // Torna o code acessível fora da classe;
        return code;
    }

    public static AdCategoryEnum valueOf(int code) {
        for (AdCategoryEnum value : AdCategoryEnum.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid order status code");
    }


}
