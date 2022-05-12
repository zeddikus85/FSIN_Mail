package com.axmor.uitest.framework.internal.builders;

import com.google.common.base.Strings;

public final class AndroidDriverBuilder extends AbstractDriverBuilder<DriverOptions> {
    @Override
    protected DriverOptions createOptions() {
        return new DriverOptions();
    }

    @Override
    protected void configureRemoteDriverOptions() {
        if (Strings.isNullOrEmpty(config().app().packageUrl())) {
            throw new IllegalArgumentException(
                "Необходимо задать месторасположение пакета (app.packageUrl) в конфигурационном файле");
        }

        super.configureRemoteDriverOptions();
        addAdditionalOption("deviceName", "android");
        addAdditionalOption("app", config().app().packageUrl());
        addAdditionalOption("autoGrantPermissions", true);
    }
}
