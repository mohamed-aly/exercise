export class Customer {
    public id: number;
    public name: string;
    public phone: string;
    public valid: boolean;
    public countryName: string;


    constructor(name: string, phone: string, valid: boolean, countryName: string) {
        this.name = name;
        this.phone = phone;
        this.valid = valid;
        this.countryName = countryName;
    }
}
