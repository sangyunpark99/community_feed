package org.sangyunpark99.acceptance.utils;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("test")
@Component
@Slf4j
public class DatabaseCleanUp implements InitializingBean {

    @PersistenceContext
    private EntityManager manager;
    private List<String> tableNames;
    private List<String> notGeneratedIdTableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = manager.getMetamodel().getEntities().stream()
                .filter(entity -> entity.getJavaType().getAnnotation(Entity.class) != null)
                .map(entity -> entity.getJavaType().getAnnotation(Table.class).name())
                .toList();

        // 복합키로 지정이 되는 테이블을 키값을 따로 지정해주어야 합니다.
        notGeneratedIdTableNames = List.of("community_user_relation", "community_like");
    }

    @Transactional
    public void execute(){
        manager.flush();
        manager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        for(String tableName : tableNames) {
            manager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            if(!notGeneratedIdTableNames.contains(tableName)) {
                manager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
            }
        }

        manager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }
}
