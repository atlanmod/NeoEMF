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

import java.util.Map;

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
 * @see WeakValueHashMap
 */
public class SoftValueHashMap<K,V> extends RefValueHashMap<K, V>
{
  /**
   * Constructs an empty <tt>SoftValueHashMap</tt> with the default initial
   * capacity (16) and the default load factor (0.75).
   */
  public SoftValueHashMap()
  {
    super(References.<V>getSoftReferenceCreator());
  }

  /**
   * Constructs an empty <tt>SoftValueHashMap</tt> with the specified initial
   * capacity and the default load factor (0.75).
   *
   * @param  initialCapacity the initial capacity.
   * @throws IllegalArgumentException if the initial capacity is negative.
   */
  public SoftValueHashMap(int initialCapacity)
  {
    super(References.<V>getSoftReferenceCreator(), initialCapacity);
  }

  /**
   * Constructs an empty <tt>HashMap</tt> with the specified initial
   * capacity and load factor.
   *
   * @param  initialCapacity The initial capacity.
   * @param  loadFactor      The load factor.
   * @throws IllegalArgumentException if the initial capacity is negative
   *         or the load factor is nonpositive.
   */
  public SoftValueHashMap(int initialCapacity, float loadFactor)
  {
    super(References.<V>getSoftReferenceCreator(), initialCapacity, loadFactor);
  }

  /**
   * Constructs a new <tt>HashMap</tt> with the same mappings as the
   * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
   * default load factor (0.75) and an initial capacity sufficient to
   * hold the mappings in the specified <tt>Map</tt>.
   *
   * @param   m the map whose mappings are to be placed in this map.
   * @throws  NullPointerException if the specified map is null.
   */
  public SoftValueHashMap(@NotNull Map<? extends K, ? extends V> m)
  {
    super(References.<V>getSoftReferenceCreator(), m);
  }

  /**
   * Constructs an empty <tt>SoftValueHashMap</tt> with the default initial
   * capacity (16) and the default load factor (0.75).
   * @param autoClean if <code>true</code> call {@link #removeDeadValues()} when finding a dead value
   */
  public SoftValueHashMap(boolean autoClean)
  {
    super(References.<V>getSoftReferenceCreator(), autoClean);
  }

  /**
   * Constructs an empty <tt>SoftValueHashMap</tt> with the specified initial
   * capacity and the default load factor (0.75).
   *
   * @param autoClean if <code>true</code> call {@link #removeDeadValues()} when finding a dead value
   * @param  initialCapacity the initial capacity.
   * @throws IllegalArgumentException if the initial capacity is negative.
   */
  public SoftValueHashMap(boolean autoClean, int initialCapacity)
  {
    super(References.<V>getSoftReferenceCreator(), autoClean, initialCapacity);
  }

  /**
   * Constructs an empty <tt>HashMap</tt> with the specified initial
   * capacity and load factor.
   *
   * @param autoClean if <code>true</code> call {@link #removeDeadValues()} when finding a dead value
   * @param  initialCapacity The initial capacity.
   * @param  loadFactor      The load factor.
   * @throws IllegalArgumentException if the initial capacity is negative
   *         or the load factor is nonpositive.
   */
  public SoftValueHashMap(boolean autoClean, int initialCapacity, float loadFactor)
  {
    super(References.<V>getSoftReferenceCreator(), autoClean, initialCapacity, loadFactor);
  }

  /**
   * Constructs a new <tt>HashMap</tt> with the same mappings as the
   * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
   * default load factor (0.75) and an initial capacity sufficient to
   * hold the mappings in the specified <tt>Map</tt>.
   *
   * @param autoClean if <code>true</code> call {@link #removeDeadValues()} when finding a dead value
   * @param   m the map whose mappings are to be placed in this map.
   * @throws  NullPointerException if the specified map is null.
   */
  public SoftValueHashMap(boolean autoClean, @NotNull Map<? extends K, ? extends V> m)
  {
    super(References.<V>getSoftReferenceCreator(), autoClean, m);
  }
}
