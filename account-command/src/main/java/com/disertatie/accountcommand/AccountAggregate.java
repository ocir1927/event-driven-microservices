package com.disertatie.accountcommand;



import com.costin.disertatie.api.command.CreateAccountCommand;
import com.costin.disertatie.api.command.CreditAccountCommand;
import com.costin.disertatie.api.command.DebitAccountCommand;
import com.costin.disertatie.api.entity.Status;
import com.costin.disertatie.api.event.AccountCreatedEvent;
import com.costin.disertatie.api.event.AccountCreditedEvent;
import com.costin.disertatie.api.event.AccountDebitedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String accountId;

    private double accountBalance;

    private String currency;

    private String status;

    private String owner;

    public AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand){
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.id, createAccountCommand.accountBalance, createAccountCommand.currency,createAccountCommand.owner));
    }


    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent){
        this.accountId = accountCreatedEvent.id;
        this.accountBalance = accountCreatedEvent.accountBalance;
        this.currency = accountCreatedEvent.currency;
        this.status = String.valueOf(Status.CREATED);
//        AggregateLifecycle.apply(new AccountActivatedEvent(this.id, Status.ACTIVATED));
    }
//    @EventSourcingHandler
//    protected void on(AccountActivatedEvent accountActivatedEvent){
//        this.status = String.valueOf(accountActivatedEvent.status);
//    }
    @CommandHandler
    protected void on(CreditAccountCommand creditAccountCommand){
        AggregateLifecycle.apply(new AccountCreditedEvent(creditAccountCommand.id, creditAccountCommand.creditAmount, creditAccountCommand.currency));
    }

    @EventSourcingHandler
    protected void on(AccountCreditedEvent moneyCreditedEvent){
        this.accountBalance += moneyCreditedEvent.creditAmount;
    }

    @CommandHandler
    protected void on(DebitAccountCommand debitMoneyCommand){
        AggregateLifecycle.apply(new DebitAccountCommand(debitMoneyCommand.id, debitMoneyCommand.debitAmount, debitMoneyCommand.currency));
    }

    @EventSourcingHandler
    protected void on(AccountDebitedEvent moneyDebitedEvent){
//        if (this.accountBalance >= 0 & (this.accountBalance - moneyDebitedEvent.debitAmount) < 0){
//            AggregateLifecycle.apply(new AccountHeldEvent(this.id, Status.HOLD));
//        }
        this.accountBalance -= moneyDebitedEvent.debitAmount;
    }
//    @EventSourcingHandler
//    protected void on(AccountHeldEvent accountHeldEvent){
//        this.status = String.valueOf(accountHeldEvent.status);
//    }
}
