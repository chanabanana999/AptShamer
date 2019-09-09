import { Complex } from './complex';

export class Contact {
  id: number;
  complex: Complex;
  name: string;
  phone: string;
  email: string;

  constructor(id?: number, complex?: Complex, name?: string, phone?: string, email?: string) {
    this.id = id;
    this.complex = complex;
    this.name = name;
    this.phone = phone;
    this.email = email;
  }
}
