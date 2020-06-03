package com.costin.disertatie.api.command;

import java.util.List;

public class MarkOrderPaidCommand extends BaseCommand<String> {
    public MarkOrderPaidCommand(String orderId) {
        super(orderId);
    }
}
