package com.axmor.uitest.tests;

import com.axmor.uitest.framework.Driver;
import com.axmor.uitest.pages.*;
import com.axmor.uitest.shared.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WriteMailTest extends TestBase {
    //@BeforeEach

    @Test
    @DisplayName("Написать письмо")
    void writeMailTest () {
        Driver.logger().info("Запуск сценария - Написать письмо");
        PortalMainPage.open();
        PanelsNavigation.goToChoiceInstitute();
        PortalChoiceInstitutePage.choiceRegionAndInstituteAndGoNextStep();
        PortalInputDataSenderAndRecipientPage.inputDataSenderAndRecipientAndGoNextStep();
        PortalWriteMailPage.writeMailAndGoNextStep();
        PortalPayMailPage.startPaymentBankCard();
        InvoiceboxPage.closeInvoiceboxPage();
        PortalPayMailPage.checkTextAboutSendMail();
    }
}
