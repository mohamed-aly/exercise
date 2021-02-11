import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable()
export class CustomerService {

    constructor(private http: HttpClient) {
    }

    getCustomers(countryName?, valid?): any {

        let url = `http://localhost:8080/customers?`;
        if (countryName != null) {
            url += `country=${countryName}`;
        }
        if (valid != null) {
            url += `&valid=${valid}`;
        }
        return this.http.get(url);
    }

    getCountries(): any {
        return this.http.get('http://localhost:8080/countries');
    }

    search(query: string): any {
        return this.http.get(`http://localhost:8080/search?query=${query}`);
    }
}
