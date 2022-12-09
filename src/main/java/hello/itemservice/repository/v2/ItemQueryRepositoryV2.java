package hello.itemservice.repository.v2;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hello.itemservice.domain.QItem.item;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasText;

/**
 * 복잡한 조회 쿼리용 Querydsl Repository 분리
 */
@Repository
@RequiredArgsConstructor
public class ItemQueryRepositoryV2 {

    private final JPAQueryFactory query;

    public List<Item> findAll(ItemSearchCondition condition) {

        String itemName = condition.getItemName();
        Integer maxPrice = condition.getMaxPrice();

        return query.select(item)
                .from(item)
                .where(likeItemName(itemName), lowOrEqualMaxPrice(maxPrice))
                .fetch();
    }

    private BooleanExpression likeItemName(String itemName) {
        return hasText(itemName) ? item.itemName.like(itemName) : null;
    }

    private BooleanExpression lowOrEqualMaxPrice(Integer maxPrice) {
        return isEmpty(maxPrice) ? null : item.price.loe(maxPrice);
    }

}
