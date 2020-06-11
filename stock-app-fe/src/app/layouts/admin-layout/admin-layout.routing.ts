import { Routes } from '@angular/router';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { UserProfileComponent } from '../../user-profile/user-profile.component';
import { PortofolioComponent } from '../../portofolio/portofolio.component';
import { NotificationsComponent } from '../../notifications/notifications.component';
import { StockviewComponent } from 'src/app/stockview/stockview.component';

export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user-profile',   component: UserProfileComponent },
    { path: 'portofolio',     component: PortofolioComponent },
    { path: 'notifications',  component: NotificationsComponent },
    { path: 'stockview',      component: StockviewComponent }
];
