import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { PortofolioComponent } from '../../portofolio/portofolio.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatRippleModule} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSelectModule} from '@angular/material/select';
import { StockviewComponent } from 'src/app/stockview/stockview.component';
import { SockTradeDialogComponent } from 'src/app/stockview/sock-trade-dialog/sock-trade-dialog.component';
import { MaterialModule } from 'src/app/material.module/material.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ReactiveFormsModule,
    MaterialModule
  ],
  declarations: [
    DashboardComponent,
    UserProfileComponent,
    PortofolioComponent,
    NotificationsComponent,
    StockviewComponent,
    SockTradeDialogComponent
  ]
})

export class AdminLayoutModule {}
