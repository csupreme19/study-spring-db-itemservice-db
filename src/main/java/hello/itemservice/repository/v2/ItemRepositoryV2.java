package hello.itemservice.repository.v2;

import hello.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA + Querydsl
 * 기본 CRUD는 Spring Data JPA 사용
 * 복잡한 조회 쿼리는 Querydsl 사용
 */
public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {
}
