/*
 * Copyright 2006 The Apache Software Foundation.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package serp.bytecode.lowlevel;

import java.io.*;
import serp.bytecode.visitor.*;

/**
 * A constant double value in the constant pool.
 * 
 * @author Abe White
 */
public class DoubleEntry extends Entry implements ConstantEntry {
    private double _value = 0.0;

    /**
     * Default constructor.
     */
    public DoubleEntry() {
    }

    /**
     * Constructor.
     * 
     * @param value the constant double value of this entry
     */
    public DoubleEntry(double value) {
        _value = value;
    }

    public boolean isWide() {
        return true;
    }

    public int getType() {
        return Entry.DOUBLE;
    }

    /**
     * Return the value of the constant.
     */
    public double getValue() {
        return _value;
    }

    /**
     * Set the value of the constant.
     */
    public void setValue(double value) {
        Object key = beforeModify();
        _value = value;
        afterModify(key);
    }

    public Object getConstant() {
        return new Double(getValue());
    }

    public void setConstant(Object value) {
        setValue(((Number) value).doubleValue());
    }

    public void acceptVisit(BCVisitor visit) {
        visit.enterDoubleEntry(this);
        visit.exitDoubleEntry(this);
    }

    void readData(DataInput in) throws IOException {
        _value = in.readDouble();
    }

    void writeData(DataOutput out) throws IOException {
        out.writeDouble(_value);
    }
}
