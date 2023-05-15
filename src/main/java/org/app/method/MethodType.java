package org.app.method;

import org.app.method.impl.*;

public enum MethodType {
    StretchMethod(StretchMethod.class),
    SaveStretchMethod(SaveStretchMethod.class),
    ChangeToneMethod(ChangeToneMethod.class),
    ChangeVolumeMethod(ChangeVolumeMethod.class),
    InterferenceMethod(InterferenceMethod.class),
    RandomVolumeMethod(RandomVolumeMethod.class);

    private final Class castType;
    MethodType(Class clazz) {
        this.castType = clazz;
    }

    public Class getCastType() {
        return castType;
    }
}
