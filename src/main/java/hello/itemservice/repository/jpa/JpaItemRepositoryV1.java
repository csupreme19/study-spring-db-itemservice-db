package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCondition;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaItemRepositoryV1 implements ItemRepository {

    private final EntityManager em;

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item item = em.find(Item.class, itemId);
        item.setItemName(updateParam.getItemName());
        item.setQuantity(updateParam.getQuantity());
        item.setPrice(updateParam.getPrice());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.of(em.find(Item.class, id));
    }

    @Override
    public List<Item> findAll(ItemSearchCondition condition) {
        return em.createQuery("select i from Item i " +
                        "where i.itemName = :itemName " +
                        "and i.price <= :maxPrice", Item.class)
                .setParameter("itemName", condition.getItemName())
                .setParameter("maxPrice", condition.getMaxPrice())
                .getResultList();
    }

}
