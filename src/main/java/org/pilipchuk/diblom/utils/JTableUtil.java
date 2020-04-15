package org.pilipchuk.diblom.utils;

import org.pilipchuk.diblom.dto.jtable.JTableEntity;

import java.util.function.Supplier;

public class JTableUtil {

    public static <T> JTableEntity<T> toResponse(Supplier<T> supplier) {
        try {
            T result = supplier.get();
            return new JTableEntity<>("OK", result);
        } catch (Exception e) {
            return new JTableEntity("ERROR", e.getMessage());
        }
    }

    public static JTableEntity<String> toResponse(Runnable operator) {
        return toResponse(() -> {
            operator.run();
            return "";
        });
    }
}
