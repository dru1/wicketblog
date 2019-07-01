package at.dru.wicketblog.wicket.model;

import at.dru.wicketblog.model.DefaultEntity;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.danekja.java.util.function.serializable.SerializableFunction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class EntityDataProvider<E extends DefaultEntity> implements IDataProvider<E> {

    private static final int MIN_PAGE_SIZE = 10;

    private final Class<E> entityClass;
    private final SerializableFunction<PageRequest, Page<E>> pageFunction;
    private final int pageSize;

    public EntityDataProvider(@Nonnull Class<E> entityClass, @Nonnull SerializableFunction<PageRequest, Page<E>> pageFunction, int pageSize) {
        this.entityClass = entityClass;
        this.pageFunction = pageFunction;
        this.pageSize = Math.max(pageSize, MIN_PAGE_SIZE);
    }

    @Override
    public Iterator<? extends E> iterator(long first, long count) {
        return getPage(first, count).iterator();
    }

    @Override
    public long size() {
        return getPage(0, pageSize).getTotalElements();
    }

    @Override
    public IModel<E> model(E object) {
        return new EntityModel<>(object, entityClass);
    }

    @Nonnull
    private Page<E> getPage(long first, long count) {
        Assert.isTrue(count <= pageSize, "The requested count must not exceed the page size.");
        Assert.isTrue(first % pageSize == 0, "The offset must be a multiple of: " + pageSize);
        int pageNumber = Math.toIntExact(first / pageSize);
        return pageFunction.apply(PageRequest.of(pageNumber, pageSize));
    }

}
