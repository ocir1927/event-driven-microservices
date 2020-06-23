import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ComponentsModule } from './components/components.module';
import { RouterModule } from '@angular/router';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { WatchlistComponent } from './watchlist/watchlist.component';
import { HttpClientModule } from '@angular/common/http';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LoginComponent } from './login/login.component';
import { MaterialModule } from './material.module/material.module';
import { DepositMoneyComponent } from './deposit-money/deposit-money.component';
import { ConfirmCloseOrderDialogComponent } from './portofolio/confirm-close-order-dialog/confirm-close-order-dialog.component';
import { WithdrawMoneyComponent } from './withdraw-money/withdraw-money.component';


@NgModule({
  declarations: [
    AppComponent,
    AdminLayoutComponent,
    WatchlistComponent,
    SignUpComponent,
    LoginComponent,
    DepositMoneyComponent,
    ConfirmCloseOrderDialogComponent,
    WithdrawMoneyComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ComponentsModule,
    RouterModule,
    AppRoutingModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
