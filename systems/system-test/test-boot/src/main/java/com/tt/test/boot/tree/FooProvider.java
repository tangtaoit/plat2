package com.tt.test.boot.tree;

import com.tt.test.boot.WicketApplication;
import org.apache.wicket.extensions.markup.html.repeater.tree.ITreeProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import java.util.Iterator;

/**
 * Created by tao on 2015/4/11.
 */
public class FooProvider implements ITreeProvider<Foo> {

    private static final long serialVersionUID = 1L;

    /**
     * Construct.
     */
    public FooProvider()
    {
    }

    /**
     * Nothing to do.
     */
    @Override
    public void detach()
    {
    }

    @Override
    public Iterator<Foo> getRoots()
    {
        return WicketApplication.get().foos.iterator();
    }

    @Override
    public boolean hasChildren(Foo foo)
    {
        return foo.getParent() == null || !foo.getFoos().isEmpty();
    }

    @Override
    public Iterator<Foo> getChildren(final Foo foo)
    {
        return foo.getFoos().iterator();
    }

    /**
     * Creates a {@link FooModel}.
     */
    @Override
    public IModel<Foo> model(Foo foo)
    {
        return new FooModel(foo);
    }

    /**
     * A {@link Model} which uses an id to load its {@link Foo}.
     *
     * If {@link Foo}s were {@link Serializable} you could just use a standard {@link Model}.
     *
     * @see #equals(Object)
     * @see #hashCode()
     */
    private static class FooModel extends LoadableDetachableModel<Foo>
    {
        private static final long serialVersionUID = 1L;

        private final String id;

        public FooModel(Foo foo)
        {
            super(foo);

            id = foo.getId();
        }

        @Override
        protected Foo load()
        {
            return WicketApplication.get().getFoo(id);
        }

        /**
         * Important! Models must be identifyable by their contained object.
         */
        @Override
        public boolean equals(Object obj)
        {
            if (obj instanceof FooModel)
            {
                return ((FooModel)obj).id.equals(id);
            }
            return false;
        }

        /**
         * Important! Models must be identifyable by their contained object.
         */
        @Override
        public int hashCode()
        {
            return id.hashCode();
        }
    }
}
