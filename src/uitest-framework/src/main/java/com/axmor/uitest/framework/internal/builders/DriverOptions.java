package com.axmor.uitest.framework.internal.builders;

import java.util.Set;

import org.openqa.selenium.remote.AbstractDriverOptions;

public final class DriverOptions extends AbstractDriverOptions<DriverOptions> {
    @Override
    protected Set<String> getExtraCapabilityNames() {
        return Set.of();
    }

    @Override
    protected Object getExtraCapability(String capabilityName) {
        return null;
    }
}
