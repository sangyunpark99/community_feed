package org.sangyunpark99.common.idempotency.repository;

import lombok.RequiredArgsConstructor;
import org.sangyunpark99.common.idempotency.IdemPotencyRepository;
import org.sangyunpark99.common.idempotency.Idempotency;
import org.sangyunpark99.common.idempotency.JpaIdempotencyRepository;
import org.sangyunpark99.common.idempotency.repository.entity.IdempotencyEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class IdemPotencyRepositoryImpl implements IdemPotencyRepository {

    private final JpaIdempotencyRepository jpaIdempotencyRepository;

    @Override
    public Idempotency getByKey(String key) {
        Optional<IdempotencyEntity> idempotencyEntity = jpaIdempotencyRepository.findByIdempotencyKey(key);
        return idempotencyEntity.map(IdempotencyEntity::toIdempotency).orElse(null);
    }

    @Override
    public void save(Idempotency idempotency) {
        jpaIdempotencyRepository.save(new IdempotencyEntity(idempotency));
    }
}
