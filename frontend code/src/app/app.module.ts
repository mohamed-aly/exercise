import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule } from '@angular/common/http';

import {AppComponent} from './app.component';
import {CustomersListComponent} from './customers-list/customers-list.component';
import {CustomerService} from './customers-list/customer.service';
import {MatRadioModule} from '@angular/material/radio';
import {FormsModule} from '@angular/forms';

@NgModule({
    declarations: [
        AppComponent,
        CustomersListComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        MatRadioModule,
        FormsModule
    ],
    providers: [CustomerService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
