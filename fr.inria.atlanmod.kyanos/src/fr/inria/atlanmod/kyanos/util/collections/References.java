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

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Helper for reference creation.
 * @author <a href="mailto:rammi@caff.de">Rammi</a>
 * @version $Revision$
 */
public class References
{
  /**
   * Get a soft reference creator for a given value.
   * This creator creates references not belonging to a reference queue.
   * @param <V> value type
   * @return soft reference creator for given value type
   */
  public static <V> ReferenceCreator<SoftReference<V>, V> getSoftReferenceCreator()
  {
    return new ReferenceCreator<SoftReference<V>,V>()
    {
      @Override
	@NotNull
      public SoftReference<V> createReference(V value)
      {
        return new SoftReference<V>(value);
      }
    };
  }

  /**
   * Get a soft reference creator for a given value.
   * @param <V> value type
   * @param queue  the queue with which the reference is to be registered
   * @return soft reference creator for given value type
   */
  public static <V> ReferenceCreator<SoftReference<V>, V> getSoftReferenceCreator(@NotNull final ReferenceQueue<? super V> queue)
  {
    return new ReferenceCreator<SoftReference<V>,V>()
    {
      @Override
	@NotNull
      public SoftReference<V> createReference(V value)
      {
        return new SoftReference<V>(value, queue);
      }
    };
  }

  /**
   * Get a weak reference creator for a given value.
   * This creator creates references not belonging to a reference queue.
   * @param <V> value type
   * @return soft reference creator for given value type
   */
  public static <V> ReferenceCreator<WeakReference<V>, V> getWeakReferenceCreator()
  {
    return new ReferenceCreator<WeakReference<V>,V>()
    {
      @Override
	@NotNull
      public WeakReference<V> createReference(V value)
      {
        return new WeakReference<V>(value);
      }
    };
  }


  /**
   * Get a weak reference creator for a given value.
   * @param <V> value type
   * @param queue  the queue with which the reference is to be registered
   * @return soft reference creator for given value type
   */
  public static <V> ReferenceCreator<WeakReference<V>, V> getWeakReferenceCreator(@NotNull final ReferenceQueue<? super V> queue)
  {
    return new ReferenceCreator<WeakReference<V>,V>()
    {
      @Override
	@NotNull
      public WeakReference<V> createReference(V value)
      {
        return new WeakReference<V>(value, queue);
      }
    };
  }
}
