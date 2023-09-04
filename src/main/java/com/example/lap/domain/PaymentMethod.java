package com.example.lap.domain;

public enum PaymentMethod {
    UNKNOWN(0),
    BILL(1),
    CREDIT_CARD(2);

    public final int id;

    PaymentMethod(int id) {
        this.id = id;
    }

    public static PaymentMethod getById(int id) {
        for(PaymentMethod p : values()) {
            if(p.id == id) {
                return p;
            }
        }

        return UNKNOWN;
    }
}
