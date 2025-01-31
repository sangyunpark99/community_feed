package org.sangyunpark99.common.idempotency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.sangyunpark99.common.ui.Response;

@Getter
@AllArgsConstructor
public class Idempotency<T> {
    private final String key; // 멱등키 키값
    private final Response<T> response;
}
