package org.app;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.app.method.AbstractMethod;

@AllArgsConstructor
@NoArgsConstructor
public class ComplexMethod {
    private AbstractMethod<Object> method;
    private ComplexMethod nextElement;
}
