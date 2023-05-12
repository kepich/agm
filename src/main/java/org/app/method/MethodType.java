package org.app.method;

import org.app.method.impl.ChangeToneMethod;
import org.app.method.impl.ChangeVolumeMethod;
import org.app.method.impl.SaveStretchMethod;
import org.app.method.impl.StretchMethod;

public enum MethodType {
    M1(StretchMethod.class),
    M2(SaveStretchMethod.class),
    M3(ChangeToneMethod.class),
    M4(ChangeVolumeMethod.class);

    private final Class castType;
    MethodType(Class clazz) {
        this.castType = clazz;
    }

    public Class getCastType() {
        return castType;
    }
}
