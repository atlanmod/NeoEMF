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

/**
 * Reference creator.
 * This creates a reference from a value.
 * @param <R> created reference type
 * @param <V> value type
 * @see References#getSoftReferenceCreator()
 * @see References#getSoftReferenceCreator(java.lang.ref.ReferenceQueue)
 * @see References#getWeakReferenceCreator()
 * @see References#getWeakReferenceCreator(java.lang.ref.ReferenceQueue)
 */
public interface ReferenceCreator<R extends Reference<? super V>, V>
{
  /**
   * Create a reference from a value.
   * @param value value
   * @return reference
   */
  @NotNull
  R createReference(V value);
}
