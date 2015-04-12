package com.tt.plat.theme.def.tree;

import com.tt.plat.core.web.api.ModuleNode;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by tao on 2015/4/12.
 */
public class ModuleExpansion implements Set<ModuleNode>, Serializable {
    private static final long serialVersionUID = 1L;

    private static MetaDataKey<ModuleExpansion> KEY = new MetaDataKey<ModuleExpansion>()
    {
        private static final long serialVersionUID = 1L;
    };

    private Set<String> ids = new HashSet<String>();

    private boolean inverse;

    public void expandAll()
    {
        ids.clear();

        inverse = true;
    }

    public void collapseAll()
    {
        ids.clear();

        inverse = false;
    }

    @Override
    public boolean add(ModuleNode foo)
    {
        if (inverse)
        {
            return ids.remove(foo.getModule().getFlag());
        }
        else
        {
            return ids.add(foo.getModule().getFlag());
        }
    }

    @Override
    public boolean remove(Object o)
    {
        ModuleNode foo = (ModuleNode)o;

        if (inverse)
        {
            return ids.add(foo.getModule().getFlag());
        }
        else
        {
            return ids.remove(foo.getModule().getFlag());
        }
    }

    @Override
    public boolean contains(Object o)
    {
        ModuleNode foo = (ModuleNode)o;

        if (inverse)
        {
            return !ids.contains(foo.getModule().getFlag());
        }
        else
        {
            return ids.contains(foo.getModule().getFlag());
        }
    }

    @Override
    public void clear()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public <A> A[] toArray(A[] a)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<ModuleNode> iterator()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray()
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends ModuleNode> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the expansion for the session.
     *
     * @return expansion
     */
    public static ModuleExpansion get()
    {
        ModuleExpansion expansion = Session.get().getMetaData(KEY);
        if (expansion == null)
        {
            expansion = new ModuleExpansion();

            Session.get().setMetaData(KEY, expansion);
        }
        return expansion;
    }

}
