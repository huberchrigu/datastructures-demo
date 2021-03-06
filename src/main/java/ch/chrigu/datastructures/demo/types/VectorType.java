/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Christoph Huber
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ch.chrigu.datastructures.demo.types;

import java.util.Collection;
import java.util.Vector;

/**
 * Provides creation of Vector instances.
 *
 * Created by Christoph Huber on 05.03.2015.
 */
public class VectorType<T> implements CollectionType<T> {
    @Override
    public Collection<T> createInstance() {
        return new Vector<>();
    }

    @Override
    public boolean allowsNoParams() {
        return true;
    }

    @Override
    public Collection<T> createInstance(int capacity) {
        return new Vector<>(capacity);
    }

    @Override
    public boolean allowsCapacityParam() {
        return true;
    }
}
