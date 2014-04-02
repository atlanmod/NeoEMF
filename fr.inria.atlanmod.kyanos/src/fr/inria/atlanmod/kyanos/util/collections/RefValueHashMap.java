// ============================================================================
// COPYRIGHT NOTICE
// ----------------------------------------------------------------------------
// (This is the open source ISC license, see
// http://en.wikipedia.org/wiki/ISC_license
// for more info)
//
// Copyright © 2012  Andreas M. Rammelt <rammi@caff.de>
//
// Permission to use, copy, modify, and/or distribute this software for any
// purpose with or without fee is hereby granted, provided that the above
// copyright notice and this permission notice appear in all copies.
//
// THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
// WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
// MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
// ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
// WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
// ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
// OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE
//=============================================================================
// Latest version on http://caff.de/generics/
//=============================================================================
package fr.inria.atlanmod.kyanos.util.collections;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * A hashmap with strong keys and soft referenced values.
 * According to the Java specification the references to the weak values are removed on garbage collection
 * if there is no more memory available.
 * See also {@link WeakValueHashMap} for a hash map with weak referenced values.
 *
 * The behavior of this hashmap is that it is possible that a value is
 * <code>null</code> because of garbage collection although
 * {@link #containsKey(Object)} says that the associated key is defined.
 * It is able to handle <code>null</code> keys and <code>null</code> values.
 *
 * The default constructors enable auto cleaning, which will automatically remove all
 * dead values each time a dead value is detected, but there are also constructors
 * accepting a boolean autoClean parameter which allow to switch this feature off.
 *
 * Some invariances usually true for maps hold not here:
 * <ul>
 *   <li>
 *     Even when the map is not changed in between, subsequent calls to {@link #containsValue(Object)}
 *     may return different results.
 *   </li>
 *   <li>
 *     When auto cleaning is switched on, the same is true for calls to {@link #containsKey(Object)}.
 *   </li>
 *   <li>
 *     When auto cleaning is switched on, the entries in the returned set of {@link #keySet()} may
 *     no longer be keys when used.
 *   </li>
 * </ul>
 * With these restriction the best usage of this map is as a cache for values which can easily be reconstructed.
 * The only methods which should be used without any problems are the {@link #put(Object, Object)},
 * {@link #putAll(java.util.Map)} and {@link #get(Object)} methods, when expecting a <code>null</code> return
 * on the latter even if a value was added before.
 *
 * @author <a href="mailto:rammi@caff.de">Rammi</a>
 * @version $Revision$
 * @see SoftValueHashMap
 * @see WeakValueHashMap
 */
public class RefValueHashMap<K,V> implements Map<K,V>
{
  /** Reference creator for creating references. */
  private final ReferenceCreator<? extends Reference<V>, V> referenceCreator;
  /** The underlying hashmap. */
  private final HashMap<K,Reference<V>> baseMap;
  /** Clean automatically. */
  private final boolean autoClean;

  /**
   * Constructs an empty <tt>SoftValueHashMap</tt> with the default initial
   * capacity (16) and the default load factor (0.75).
   * @param creator reference creator
   */
  public RefValueHashMap(@NotNull ReferenceCreator<? extends Reference<V>, V> creator)
  {
    this(creator, true);
  }

  /**
   * Constructs an empty <tt>SoftValueHashMap</tt> with the specified initial
   * capacity and the default load factor (0.75).
   *
   * @param creator reference creator
   * @param  initialCapacity the initial capacity.
   * @throws IllegalArgumentException if the initial capacity is negative.
   */
  public RefValueHashMap(@NotNull ReferenceCreator<? extends Reference<V>, V> creator, int initialCapacity)
  {
    this(creator, true, initialCapacity, 0.75f);
  }

  /**
   * Constructs an empty <tt>HashMap</tt> with the specified initial
   * capacity and load factor.
   *
   * @param creator reference creator
   * @param  initialCapacity The initial capacity.
   * @param  loadFactor      The load factor.
   * @throws IllegalArgumentException if the initial capacity is negative
   *         or the load factor is nonpositive.
   */
  public RefValueHashMap(@NotNull ReferenceCreator<? extends Reference<V>, V> creator, int initialCapacity, float loadFactor)
  {
    this(creator, true, initialCapacity, loadFactor);
  }

  /**
   * Constructs a new <tt>HashMap</tt> with the same mappings as the
   * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
   * default load factor (0.75) and an initial capacity sufficient to
   * hold the mappings in the specified <tt>Map</tt>.
   *
   * @param creator reference creator
   * @param   m the map whose mappings are to be placed in this map.
   * @throws  NullPointerException if the specified map is null.
   */
  public RefValueHashMap(@NotNull ReferenceCreator<? extends Reference<V>, V> creator, @NotNull Map<? extends K, ? extends V> m)
  {
    this(creator, true, m);
  }

  /**
   * Constructs an empty <tt>SoftValueHashMap</tt> with the default initial
   * capacity (16) and the default load factor (0.75).
   * @param creator reference creator
   * @param autoClean if <code>true</code> call {@link #removeDeadValues()} when finding a dead value
   */
  public RefValueHashMap(@NotNull ReferenceCreator<? extends Reference<V>, V> creator, boolean autoClean)
  {
    this.referenceCreator = creator;
    this.autoClean = autoClean;
    baseMap = new HashMap<K, Reference<V>>();
  }

  /**
   * Constructs an empty <tt>SoftValueHashMap</tt> with the specified initial
   * capacity and the default load factor (0.75).
   *
   * @param creator reference creator
   * @param autoClean if <code>true</code> call {@link #removeDeadValues()} when finding a dead value
   * @param  initialCapacity the initial capacity.
   * @throws IllegalArgumentException if the initial capacity is negative.
   */
  public RefValueHashMap(@NotNull ReferenceCreator<? extends Reference<V>, V> creator, boolean autoClean, int initialCapacity)
  {
    this.referenceCreator = creator;
    this.autoClean = autoClean;
    baseMap = new HashMap<K, Reference<V>>(initialCapacity);
  }

  /**
   * Constructs an empty <tt>HashMap</tt> with the specified initial
   * capacity and load factor.
   *
   * @param creator reference creator
   * @param autoClean if <code>true</code> call {@link #removeDeadValues()} when finding a dead value
   * @param  initialCapacity The initial capacity.
   * @param  loadFactor      The load factor.
   * @throws IllegalArgumentException if the initial capacity is negative
   *         or the load factor is nonpositive.
   */
  public RefValueHashMap(@NotNull ReferenceCreator<? extends Reference<V>, V> creator,boolean autoClean, int initialCapacity, float loadFactor)
  {
    this.referenceCreator = creator;
    this.autoClean = autoClean;
    baseMap = new HashMap<K, Reference<V>>(initialCapacity, loadFactor);
  }

  /**
   * Constructs a new <tt>HashMap</tt> with the same mappings as the
   * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
   * default load factor (0.75) and an initial capacity sufficient to
   * hold the mappings in the specified <tt>Map</tt>.
   *
   * @param creator reference creator
   * @param autoClean if <code>true</code> call {@link #removeDeadValues()} when finding a dead value
   * @param   m the map whose mappings are to be placed in this map.
   * @throws  NullPointerException if the specified map is null.
   */
  public RefValueHashMap(@NotNull ReferenceCreator<? extends Reference<V>, V> creator, boolean autoClean, @NotNull Map<? extends K, ? extends V> m)
  {
    this.referenceCreator = creator;
    this.autoClean = autoClean;
    baseMap = new HashMap<K, Reference<V>>(Math.max((int)(m.size()/0.75) + 1, 16));
    putAll(m);
  }

  /**
   * Returns the number of key-value mappings in this map.  If the
   * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
   * <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of key-value mappings in this map.
   */
  @Override
public int size()
  {
    return baseMap.size();
  }

  /**
   * Returns <tt>true</tt> if this map contains no key-value mappings.
   *
   * @return <tt>true</tt> if this map contains no key-value mappings.
   */
  @Override
public boolean isEmpty()
  {
    return baseMap.isEmpty();
  }

  /**
   * Returns <tt>true</tt> if this map contains a mapping for the specified
   * key.  More formally, returns <tt>true</tt> if and only if
   * this map contains a mapping for a key <tt>k</tt> such that
   * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
   * at most one such mapping.)
   *
   * @param key key whose presence in this map is to be tested.
   * @return <tt>true</tt> if this map contains a mapping for the specified
   *         key.
   * @throws ClassCastException   if the key is of an inappropriate type for
   *                              this map (optional).
   * @throws NullPointerException if the key is <tt>null</tt> and this map
   *                              does not permit <tt>null</tt> keys (optional).
   */
  @Override
public boolean containsKey(Object key)
  {
    return baseMap.containsKey(key);
  }

  /**
   * Returns <tt>true</tt> if this map maps one or more keys to the
   * specified value.  More formally, returns <tt>true</tt> if and only if
   * this map contains at least one mapping to a value <tt>v</tt> such that
   * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
   * will probably require time linear in the map size for most
   * implementations of the <tt>Map</tt> interface.
   *
   * @param value value whose presence in this map is to be tested.
   * @return <tt>true</tt> if this map maps one or more keys to the
   *         specified value.
   * @throws ClassCastException   if the value is of an inappropriate type for
   *                              this map (optional).
   * @throws NullPointerException if the value is <tt>null</tt> and this map
   *                              does not permit <tt>null</tt> values (optional).
   */
  @Override
public boolean containsValue(Object value)
  {
    value = wrapNull(value);
    for (Reference<V> v : baseMap.values()) {
      if (value.equals(v.get())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the value to which this map maps the specified key.  Returns
   * <tt>null</tt> if the map contains no mapping for this key.  A return
   * value of <tt>null</tt> does not <i>necessarily</i> indicate that the
   * map contains no mapping for the key; it's also possible that the map
   * explicitly maps the key to <tt>null</tt>.  The <tt>containsKey</tt>
   * operation may be used to distinguish these two cases.
   * <p/>
   * <p>More formally, if this map contains a mapping from a key
   * <tt>k</tt> to a value <tt>v</tt> such that <tt>(key==null ? k==null :
   * key.equals(k))</tt>, then this method returns <tt>v</tt>; otherwise
   * it returns <tt>null</tt>.  (There can be at most one such mapping.)
   *
   * @param key key whose associated value is to be returned.
   * @return the value to which this map maps the specified key, or
   *         <tt>null</tt> if the map contains no mapping for this key.
   * @throws ClassCastException   if the key is of an inappropriate type for
   *                              this map (optional).
   * @throws NullPointerException if the key is <tt>null</tt> and this map
   *                              does not permit <tt>null</tt> keys (optional).
   * @see #containsKey(Object)
   */
  @Override
public V get(Object key)
  {
    return unwrap(baseMap.get(key));
  }

  /**
   * Associates the specified value with the specified key in this map
   * (optional operation).  If the map previously contained a mapping for
   * this key, the old value is replaced by the specified value.  (A map
   * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
   * if {@link #containsKey(Object) m.containsKey(k)} would return
   * <tt>true</tt>.))
   *
   * @param key   key with which the specified value is to be associated.
   * @param value value to be associated with the specified key.
   * @return previous value associated with specified key, or <tt>null</tt>
   *         if there was no mapping for key.  A <tt>null</tt> return can
   *         also indicate that the map previously associated <tt>null</tt>
   *         with the specified key, if the implementation supports
   *         <tt>null</tt> values.
   * @throws UnsupportedOperationException if the <tt>put</tt> operation is
   *                                       not supported by this map.
   * @throws ClassCastException            if the class of the specified key or value
   *                                       prevents it from being stored in this map.
   * @throws IllegalArgumentException      if some aspect of this key or value
   *                                       prevents it from being stored in this map.
   * @throws NullPointerException          if this map does not permit <tt>null</tt>
   *                                       keys or values, and the specified key or value is
   *                                       <tt>null</tt>.
   */
  @Override
public V put(K key, V value)
  {
    baseMap.put(key, referenceCreator.createReference(wrapNull(value)));
    return value;
  }

  /**
   * Removes the mapping for this key from this map if it is present
   * (optional operation).   More formally, if this map contains a mapping
   * from key <tt>k</tt> to value <tt>v</tt> such that
   * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
   * is removed.  (The map can contain at most one such mapping.)
   * <p/>
   * <p>Returns the value to which the map previously associated the key, or
   * <tt>null</tt> if the map contained no mapping for this key.  (A
   * <tt>null</tt> return can also indicate that the map previously
   * associated <tt>null</tt> with the specified key if the implementation
   * supports <tt>null</tt> values.)  The map will not contain a mapping for
   * the specified  key once the call returns.
   *
   * @param key key whose mapping is to be removed from the map.
   * @return previous value associated with specified key, or <tt>null</tt>
   *         if there was no mapping for key.
   * @throws ClassCastException            if the key is of an inappropriate type for
   *                                       this map (optional).
   * @throws NullPointerException          if the key is <tt>null</tt> and this map
   *                                       does not permit <tt>null</tt> keys (optional).
   * @throws UnsupportedOperationException if the <tt>remove</tt> method is
   *                                       not supported by this map.
   */
  @Override
public V remove(Object key)
  {
    return unwrap(baseMap.remove(key));
  }

  /**
   * Copies all of the mappings from the specified map to this map
   * (optional operation).  The effect of this call is equivalent to that
   * of calling {@link #put(Object, Object) put(k, v)} on this map once
   * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
   * specified map.  The behavior of this operation is unspecified if the
   * specified map is modified while the operation is in progress.
   *
   * @param t Mappings to be stored in this map.
   * @throws UnsupportedOperationException if the <tt>putAll</tt> method is
   *                                       not supported by this map.
   * @throws ClassCastException            if the class of a key or value in the
   *                                       specified map prevents it from being stored in this map.
   * @throws IllegalArgumentException      some aspect of a key or value in the
   *                                       specified map prevents it from being stored in this map.
   * @throws NullPointerException          if the specified map is <tt>null</tt>, or if
   *                                       this map does not permit <tt>null</tt> keys or values, and the
   *                                       specified map contains <tt>null</tt> keys or values.
   */
  @Override
public void putAll(Map<? extends K, ? extends V> t)
  {
    for (final Entry<? extends K, ? extends V> entry : t.entrySet()) {
      baseMap.put(entry.getKey(), referenceCreator.createReference(wrapNull(entry.getValue())));
    }
  }

  /**
   * Removes all mappings from this map (optional operation).
   *
   * @throws UnsupportedOperationException clear is not supported by this
   *                                       map.
   */
  @Override
public void clear()
  {
    baseMap.clear();
  }

  /**
   * Returns a set view of the keys contained in this map.  The set is
   * backed by the map, so changes to the map are reflected in the set, and
   * vice-versa.  If the map is modified while an iteration over the set is
   * in progress (except through the iterator's own <tt>remove</tt>
   * operation), the results of the iteration are undefined.  The set
   * supports element removal, which removes the corresponding mapping from
   * the map, via the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
   * <tt>removeAll</tt> <tt>retainAll</tt>, and <tt>clear</tt> operations.
   * It does not support the add or <tt>addAll</tt> operations.
   *
   * @return a set view of the keys contained in this map.
   */
  @Override
public Set<K> keySet()
  {
    return baseMap.keySet();
  }

  /**
   * Returns a collection view of the values contained in this map.  The
   * collection is backed by the map, so changes to the map are reflected in
   * the collection, and vice-versa.  If the map is modified while an
   * iteration over the collection is in progress (except through the
   * iterator's own <tt>remove</tt> operation), the results of the
   * iteration are undefined.  The collection supports element removal,
   * which removes the corresponding mapping from the map, via the
   * <tt>Iterator.remove</tt>, <tt>Collection.remove</tt>,
   * <tt>removeAll</tt>, <tt>retainAll</tt> and <tt>clear</tt> operations.
   * It does not support the add or <tt>addAll</tt> operations.
   *
   * @return a collection view of the values contained in this map.
   */
  @Override
public Collection<V> values()
  {
    Collection<Reference<V>> refs = baseMap.values();
    Collection<V> result = new ArrayList<V>(refs.size());
    for (Reference<V> ref: refs) {
      result.add(unwrap(ref));
    }
    return result;
  }

  /**
   * Returns a set view of the mappings contained in this map.  Each element
   * in the returned set is a {@link java.util.Map.Entry}.  The set is backed by the
   * map, so changes to the map are reflected in the set, and vice-versa.
   * If the map is modified while an iteration over the set is in progress
   * (except through the iterator's own <tt>remove</tt> operation, or through
   * the <tt>setValue</tt> operation on a map entry returned by the iterator)
   * the results of the iteration are undefined.  The set supports element
   * removal, which removes the corresponding mapping from the map, via the
   * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
   * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not support
   * the <tt>add</tt> or <tt>addAll</tt> operations.
   *
   * @return a set view of the mappings contained in this map.
   */
  @Override
public Set<Entry<K, V>> entrySet()
  {
    Set<Entry<K, Reference<V>>> baseSet = baseMap.entrySet();
    Set<Entry<K, V>> result = new HashSet<Entry<K,V>>();
    for (Entry<K, Reference<V>> entry: baseSet) {
      final K key = entry.getKey();
      final V value = unwrap(entry.getValue());
      result.add(new Entry<K, V>() {
        @Override
		public K getKey()
        {
          return key;
        }

        /**
         * Returns the value corresponding to this entry.  If the mapping
         * has been removed from the backing map (by the iterator's
         * <tt>remove</tt> operation), the results of this call are undefined.
         *
         * @return the value corresponding to this entry.
         * @throws IllegalStateException implementations may, but are not
         *                               required to, throw this exception if the entry has been
         *                               removed from the backing map
         */
        @Override
		public V getValue()
        {
          return value;
        }

        /**
         * Replaces the value corresponding to this entry with the specified
         * value (optional operation).  (Writes through to the map.)  The
         * behavior of this call is undefined if the mapping has already been
         * removed from the map (by the iterator's <tt>remove</tt> operation).
         *
         * @param value new value to be stored in this entry.
         * @return old value corresponding to the entry.
         * @throws UnsupportedOperationException if the <tt>put</tt> operation
         *                                       is not supported by the backing map.
         * @throws ClassCastException            if the class of the specified value
         *                                       prevents it from being stored in the backing map.
         * @throws IllegalArgumentException      if some aspect of this value
         *                                       prevents it from being stored in the backing map.
         * @throws NullPointerException          if the backing map does not permit
         *                                       <tt>null</tt> values, and the specified value is
         *                                       <tt>null</tt>.
         * @throws IllegalStateException         implementations may, but are not
         *                                       required to, throw this exception if the entry has been
         *                                       removed from the backing map
         */
        @Override
		public V setValue(V value)
        {
          return put(key, value);
        }
      });
    }
    return result;
  }

  /**
   * Return whether the value for a given key was garbage collected.
   * @param key key to look for
   * @return <code>true</code> the value was garbage collected and is no longer available,<br/>
   *         <code>false</code> the value is still available (but may be gone soon)
   */
  public boolean hasDeadValue(K key)
  {
    Reference<V> ref = baseMap.get(key);
    return ref.get() != null;
  }

  /**
   * Remove all entries from this map which have dead values.
   */
  public void removeDeadValues()
  {
    for (K key : new ArrayList<K>(baseMap.keySet())) {
      if (hasDeadValue(key)) {
        baseMap.remove(key);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private V wrapNull(Object value)
  {
    return (V)(value != null ? value : baseMap); // looks evil, but is okay
  }

  private V unwrap(Reference<V> ref) {
    if (ref == null) {
      return null;
    }
    V value = ref.get();
    if (autoClean && value == null) {
      removeDeadValues();
    }
    return value != baseMap ? value : null;

  }
}
