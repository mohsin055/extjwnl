package net.sf.extjwnl.util.cache;

import net.sf.extjwnl.data.POS;

/**
 * A set of Caches split by POS.
 *
 * @author Aliaksandr Autayeu <aliaksandr@autayeu.com>
 */
public interface POSCache<K, V> {

    Cache<K, V> getCache(POS pos);
}
