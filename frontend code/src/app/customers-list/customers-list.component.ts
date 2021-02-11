import {Component, OnInit} from '@angular/core';
import {CustomerService} from './customer.service';
import {Customer} from './customer.model';
import {FormsModule} from '@angular/forms';

@Component({
    selector: 'app-customers-list',
    templateUrl: './customers-list.component.html',
    styleUrls: ['./customers-list.component.css']
})
export class CustomersListComponent implements OnInit {

    constructor(private service: CustomerService, private formsModule: FormsModule) {
    }

    public customers: Customer[];
    public countries: string[];
    public countryName: string;
    public valid: any;
    public query: string;


    ngOnInit(): void {
        this.getCustomers();
        this.getCountries();
    }

    private getCustomers(): void {
        this.service.getCustomers(this.countryName, this.valid).subscribe(
            (response: any) => {
                this.customers = response;
            },
            (error: any) => {
                console.error(error);
            }
        );
    }

    public search(query: string): void {
        this.service.search(query).subscribe(
            (response: any) => {
                this.customers = response;
            },
            (error: any) => {
                console.error(error);
            }
        );
        this.query = null;
    }

    private getCountries(): void {
        this.service.getCountries().subscribe(
            (response: any) => {
                this.countries = response;
            },
            (error: any) => {
                console.error(error);
            }
        );
    }

    public onSelectionChange(change: any): void {
        this.countryName = change;
        this.getCustomers();
    }

    public onStateChange(change: any): void {
        this.valid = change;
        this.getCustomers();
    }

    public onSubmit(query: string): void {
        this.query = query;
        this.search(this.query);
    }


}
