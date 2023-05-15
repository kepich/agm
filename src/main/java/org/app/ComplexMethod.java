package org.app;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.app.method.AbstractMethod;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ComplexMethod {
    private AbstractMethod<Object> method;
    private ComplexMethod nextElement;

    public void addMethod(AbstractMethod<Object> method) {
        if (nextElement == null) {
            nextElement = new ComplexMethod(method, null);
        } else {
            nextElement.addMethod(method);
        }
    }

    public void addMethods(List<AbstractMethod<Object>> methods) {
        for (AbstractMethod<Object> method: methods){
            this.addMethod(method);
        }
    }
}
