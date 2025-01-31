package org.sangyunpark99.common.idempotency;

public interface IdemPotencyRepository {

    Idempotency getByKey(String key);
    void save(Idempotency idempotency);
}
