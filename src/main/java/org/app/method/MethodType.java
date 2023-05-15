package org.app.method;

import org.app.method.impl.ChangeToneMethod;
import org.app.method.impl.ChangeVolumeMethod;
import org.app.method.impl.InterferenceMethod;
import org.app.method.impl.RandomVolumeMethod;
import org.app.method.impl.SaveStretchMethod;
import org.app.method.impl.StretchMethod;

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
